package br.com.api.geofusion.cart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.geofusion.cart.models.Client;
import br.com.api.geofusion.cart.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Client> registerClient(Client client) {
        return new ResponseEntity<Client>(clientRepository.save(client), HttpStatus.CREATED);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

}
