package br.com.api.geofusion.cart.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Entity
@Table(name = "items")
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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private BigDecimal unitPrice;

    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "")
    private ShoppingCart shoppingCart;

    
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

    // Comparação entre objetos pelo id.
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}

