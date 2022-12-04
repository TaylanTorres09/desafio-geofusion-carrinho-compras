package br.com.api.geofusion.cart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.geofusion.cart.models.ShoppingCart;
import br.com.api.geofusion.cart.services.ShoppingCartFactory;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartFactory shoppingCart;

    @PostMapping("/register/{clientId}")
    public ShoppingCart registerItem(@PathVariable(name = "clientId") String clientId) {
        return shoppingCart.create(clientId);
    }

    // Teste
    @GetMapping("/{clientId}")
    public List<ShoppingCart> findByClient(@PathVariable(name = "clientId") Long clientId) {
        return shoppingCart.findByClient(clientId);
    }

}
