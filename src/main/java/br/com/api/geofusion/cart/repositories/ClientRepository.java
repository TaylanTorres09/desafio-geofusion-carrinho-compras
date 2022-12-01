package br.com.api.geofusion.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.geofusion.cart.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
