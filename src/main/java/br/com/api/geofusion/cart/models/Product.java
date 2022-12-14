package br.com.api.geofusion.cart.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Transformar o atributo code em PrimaryKey, pois se 2 produtos são considerandos
     * iguais com o mesmo código então eles não podem ter descrições diferentes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Item> items = new ArrayList<>();

    /**
     * Construtor da classe Produto.
     *
     * @param code
     * @param description
     */
    public Product(){
    }

    public Product(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Retorna o código da produto.
     *
     * @return Long
     */
    public Long getCode() {
        return code;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna a lista de itens que o produto está inserido.
     *
     * @return List<Item>
     */
    public List<Item> getItems() {
        return items;
    }


    public void setCode(Long code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        Product other = (Product) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}