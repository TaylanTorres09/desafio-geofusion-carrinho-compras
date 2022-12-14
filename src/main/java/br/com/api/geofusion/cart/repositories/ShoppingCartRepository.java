package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.geofusion.cart.models.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
}
