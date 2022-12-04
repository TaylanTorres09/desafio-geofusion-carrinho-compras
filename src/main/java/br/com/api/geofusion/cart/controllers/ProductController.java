package br.com.api.geofusion.cart.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.geofusion.cart.dto.ProductDto;
import br.com.api.geofusion.cart.models.Product;
import br.com.api.geofusion.cart.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return productService.registerProduct(product);
    }

    // Teste
    @GetMapping()
    public List<Product> findAll(){
        return productService.findAll();
    }

}
