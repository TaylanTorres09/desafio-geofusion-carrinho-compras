package br.com.api.geofusion.cart.models;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
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

    public void setCode(Long code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}