package br.com.api.geofusion.cart.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.dto.ItemDto;
import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.models.ShoppingCart;
import br.com.api.geofusion.cart.repositories.ClientRepository;
import br.com.api.geofusion.cart.repositories.ItemRepository;
import br.com.api.geofusion.cart.repositories.ShoppingCartRepository;
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

    @Autowired ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

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
