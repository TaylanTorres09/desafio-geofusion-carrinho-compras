package br.com.api.geofusion.cart.dto;

import jakarta.validation.constraints.NotEmpty;

public class ProductDto {

    @NotEmpty
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
