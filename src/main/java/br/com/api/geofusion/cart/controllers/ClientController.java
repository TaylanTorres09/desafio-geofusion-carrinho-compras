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

import br.com.api.geofusion.cart.dto.ClientDto;
import br.com.api.geofusion.cart.models.Client;
import br.com.api.geofusion.cart.services.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@Valid @RequestBody ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return clientService.registerClient(client);
    }

    @GetMapping()
    public List<Client> findAllClients() {
        return clientService.findAllClients();
    }
}
