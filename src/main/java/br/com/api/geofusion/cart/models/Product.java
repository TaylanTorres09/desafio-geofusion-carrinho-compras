package br.com.api.geofusion.cart.models;

import java.io.Serializable;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private Long code;
    private String description;

    /**
     * Construtor da classe Produto.
     *
     * @param code
     * @param description
     */
    public Product(Long code, String description) {
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

    public void setCode(Long code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}