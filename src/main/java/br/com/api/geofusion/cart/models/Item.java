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

    private Integer quantity;

    
    public Item(){
    }

    
    public Item(Long id, Product product, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    
     public Product getProduct() {
        return null;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return null;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return 0;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return null;
    }
}

