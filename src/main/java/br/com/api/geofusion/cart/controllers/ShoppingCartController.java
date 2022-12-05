package br.com.api.geofusion.cart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.geofusion.cart.dto.ItemDto;
import br.com.api.geofusion.cart.models.ShoppingCart;
import br.com.api.geofusion.cart.services.ShoppingCartFactory;
import jakarta.validation.Valid;

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

    // Teste
    @PutMapping("/add-item-test")
    public ShoppingCart addItemTest(@RequestParam(name = "clientId") Long clientId, @RequestParam Long itemId) {
        return shoppingCart.addItem(clientId, itemId);
    }

    @PutMapping("/add-item")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemDto itemDto, 
                                @RequestParam(name = "clientId") Long clientId, 
                                @RequestParam Long productId
    ) {
        return shoppingCart.addItemCart(itemDto, clientId, productId);
    }

}
