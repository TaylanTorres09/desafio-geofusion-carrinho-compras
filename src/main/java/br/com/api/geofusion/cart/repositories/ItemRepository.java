package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.geofusion.cart.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
