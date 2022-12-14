package br.com.api.geofusion.cart.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.api.geofusion.cart.models.Client;
import br.com.api.geofusion.cart.models.Item;
import br.com.api.geofusion.cart.models.Product;
import br.com.api.geofusion.cart.repositories.ClientRepository;
import br.com.api.geofusion.cart.repositories.ItemRepository;
import br.com.api.geofusion.cart.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client(null, "teste", "teste@teste.com", "12345");
        clientRepository.save(client);

        Product product = new Product(null, "teste");
        productRepository.save(product);
        
        Item item = new Item(null, product, BigDecimal.valueOf(300.00), 2);
        itemRepository.save(item);
        
        product.getItems().add(item);
        productRepository.save(product);

    }
    
}
