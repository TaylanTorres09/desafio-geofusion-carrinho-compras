package br.com.api.geofusion.cart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ClientDto {
    
    @NotEmpty(message = "Nome Obrigatório")
    private String name;

    @NotEmpty(message = "Email Obrigatório")
    @Email(message = "Exemplo: client@client.com")
    private String email;

    @NotEmpty(message = "Senha Obrigatória")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
