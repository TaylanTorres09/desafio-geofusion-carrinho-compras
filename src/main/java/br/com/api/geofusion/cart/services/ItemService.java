package br.com.api.geofusion.cart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.repositories.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<?> registerItem(Item item) {
        return new ResponseEntity<Item>(itemRepository.save(item), HttpStatus.CREATED);
    }

}
