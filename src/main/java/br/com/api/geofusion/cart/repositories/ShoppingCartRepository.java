package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.geofusion.cart.models.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
    
}
