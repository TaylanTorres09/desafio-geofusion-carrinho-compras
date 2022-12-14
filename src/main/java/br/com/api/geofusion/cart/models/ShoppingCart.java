package br.com.api.geofusion.cart.models;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Entity
@Table(name = "shopping-carts")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart")
    private List<Item> items;

    @OneToOne
    @MapsId
    private Client client;

    public ShoppingCart(){
    }

    public ShoppingCart(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public void addItem(Product product, BigDecimal unitPrice, int quantity) {
        Item item = new Item(null, product, unitPrice, quantity);
        this.items.add(item);
    }
    
    // Outra opção para função addItem.
    public void addItemTest(Item item) {
        this.items.add(item);
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(Product product) {
        List<Item> itemsCart = items.stream().filter(item -> item.getProduct().getCode() == product.getCode()).toList();
        if(itemsCart.isEmpty())
            return false;
        return true;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na
     * coleção, em que zero representa o primeiro item.
     *
     * @param itemIndex
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(int itemIndex) {
        try {
            items.get(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        BigDecimal resultAmount = items.stream()
                                    .map(item -> item.getAmount())
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return resultAmount;
    }

    public Long clientId(){
        return this.client.getId();
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}