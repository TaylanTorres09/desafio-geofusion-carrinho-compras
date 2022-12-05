package br.com.api.geofusion.cart.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.dto.ItemDto;
import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.models.Product;
import br.com.api.geofusion.cart.models.ShoppingCart;
import br.com.api.geofusion.cart.repositories.ClientRepository;
import br.com.api.geofusion.cart.repositories.ItemRepository;
import br.com.api.geofusion.cart.repositories.ShoppingCartRepository;
import br.com.api.geofusion.cart.services.exceptions.DatabaseException;
import br.com.api.geofusion.cart.services.exceptions.ResourceNotFoundException;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Service
public class ShoppingCartFactory {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired 
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductService productService;

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCart
     */
    public ShoppingCart create(String clientId) {
        Long _clientId = Long.parseLong(clientId);

        if(!findByClient(_clientId).isEmpty()){
            return findByClient(_clientId).get(0);
        }

        ShoppingCart _shoppingCart = new ShoppingCart(null, new ArrayList<Item>());
        ShoppingCart shoppingCart = clientRepository.findById(_clientId).map(client -> {
            _shoppingCart.setClient(client);
            return shoppingCartRepository.save(_shoppingCart);
        }).orElseThrow(() -> new ResourceNotFoundException(_clientId));

        return shoppingCart;
    }

    /**
     * Função para retornar carrinho do cliente
     * @param clientId
     * @return
     */
    public List<ShoppingCart> findByClient(Long clientId) {
        return shoppingCartRepository.findAll().stream()
                                        .filter(cart -> cart.getClient().getId() == clientId).toList();
    }

    // Teste
    public ShoppingCart addItem(Long clientId, Long itemId){
        ShoppingCart shoppingCart = findByClient(clientId).get(0);

        ShoppingCart _shoppingCart = itemRepository.findById(itemId).map(item -> {
            shoppingCart.addItemTest(item);
            item.setShoppingCart(shoppingCart);
            itemRepository.save(item);
            return shoppingCartRepository.save(shoppingCart);
        }).orElseThrow(() -> new ResourceNotFoundException(itemId));

        return _shoppingCart;
    }


    /**
     * Função para adicionar item no carrinho
     * @param itemDto
     * @param clientId
     * @param productId
     * @return
     */
    public ResponseEntity<?> addItemCart(ItemDto itemDto, Long clientId, Long productId) {
        try {
            ShoppingCart shoppingCart = findByClient(clientId).get(0);
            List<Item> itemsCart = shoppingCart.getItems().stream().filter(item -> item.getProduct().getCode() == productId).toList();
            if(itemsCart.isEmpty()){
                Item item = new Item();
                BeanUtils.copyProperties(itemDto, item);
                itemService.registerItem(item, productId);
                ShoppingCart _shoppingCart = addItem(clientId, item.getId());
                return new ResponseEntity<ShoppingCart>(_shoppingCart, HttpStatus.OK);
            }
            Long idItem = itemsCart.get(0).getId();
            itemService.updateItem(itemDto, idItem);
            return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Remove Item do carrinho passando o produto como parametro.
     * @param clientId
     * @param productId
     * @return
     */
    public ResponseEntity<?> deleteProductCart(Long clientId, Long productId){
        try {
            Product product = productService.findById(productId);

            List<ShoppingCart> shoppingCarts = findByClient(clientId);
            if(shoppingCarts.isEmpty())
                return new ResponseEntity<String>("Client without cart", HttpStatus.BAD_GATEWAY);
            
            ShoppingCart shoppingCart = shoppingCarts.get(0);
            
            if(shoppingCart.removeItem(product)) {
                List<Item> itemsCart = shoppingCart.getItems().stream().filter(item -> item.getProduct().getCode() == product.getCode()).toList();
                shoppingCart.getItems().remove(itemsCart.get(0));
                shoppingCartRepository.save(shoppingCart);

                itemRepository.delete(itemsCart.get(0));
                return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Product not exists in Cart", HttpStatus.BAD_REQUEST);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(productId);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(productId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketAmount() {
        return null;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param clientId
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(String clientId) {
        return false;
    }
}
