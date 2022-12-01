package br.com.api.geofusion.cart.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class ItemDto {
    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private int quantity;

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
