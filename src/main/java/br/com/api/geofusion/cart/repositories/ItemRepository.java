package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.geofusion.cart.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}