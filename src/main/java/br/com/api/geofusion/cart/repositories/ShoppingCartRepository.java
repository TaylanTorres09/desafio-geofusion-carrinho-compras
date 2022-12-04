package br.com.api.geofusion.cart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.geofusion.cart.models.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

    List<ShoppingCart> findByClient(Long clientId);
    
}
