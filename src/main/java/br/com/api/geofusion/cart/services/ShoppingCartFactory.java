package br.com.api.geofusion.cart.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.models.ShoppingCart;
import br.com.api.geofusion.cart.repositories.ClientRepository;
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

        ShoppingCart _shoppingCart = new ShoppingCart(new ArrayList<>());
        ShoppingCart shoppingCart = clientRepository.findById(_clientId).map(client -> {
            _shoppingCart.setClient(client);
            return shoppingCartRepository.save(_shoppingCart);
        }).orElseThrow(() -> new ResourceNotFoundException(_clientId));

        return shoppingCart;
    }

    public List<ShoppingCart> findByClient(Long clientId) {
        return shoppingCartRepository.findAll().stream()
                                        .filter(cart -> cart.getClient().getId() == clientId).toList();
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
