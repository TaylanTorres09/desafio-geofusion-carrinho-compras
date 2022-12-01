package br.com.api.geofusion.cart.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.geofusion.cart.dto.ItemDto;
import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.services.ItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @PostMapping("/register/{productId}")
    public ResponseEntity<?> registerItem(@Valid @RequestBody ItemDto itemDto, @PathVariable(name = "productId") Long productId) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        return itemService.registerItem(item, productId);
    }

}
