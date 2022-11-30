package br.com.api.geofusion.cart.models;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe Item.
     *
     * @param product
     * @param unitPrice
     * @param quantity
    **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Product product;
    
    private BigDecimal unitPrice;

    private int quantity;

    
    public Item(){
    }

    
    public Item(Long id, Product product, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    
    public Product getProduct() {
        return product;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}

