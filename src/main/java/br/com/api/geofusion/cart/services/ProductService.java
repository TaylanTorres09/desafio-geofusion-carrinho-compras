package br.com.api.geofusion.cart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.models.Product;
import br.com.api.geofusion.cart.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> registerProduct(Product product) {
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
