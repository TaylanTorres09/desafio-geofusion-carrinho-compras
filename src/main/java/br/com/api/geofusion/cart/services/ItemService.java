package br.com.api.geofusion.cart.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.dto.ItemDto;
import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.repositories.ItemRepository;
import br.com.api.geofusion.cart.repositories.ProductRepository;
import br.com.api.geofusion.cart.services.exceptions.ResourceNotFoundException;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> registerItem(Item item, Long productId) {
        Item _item = productRepository.findById(productId).map(product -> {
            item.setProduct(product);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException(productId));
        return new ResponseEntity<>(_item, HttpStatus.CREATED);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public ResponseEntity<?> updateItem(ItemDto itemDto, Long id) {
        try {
            Item item = itemRepository.findById(id).get();
            item.setUnitPrice(itemDto.getUnitPrice());

            return new ResponseEntity<Item>(itemRepository.save(item), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
