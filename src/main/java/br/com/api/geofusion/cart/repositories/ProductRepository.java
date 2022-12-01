package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.geofusion.cart.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
