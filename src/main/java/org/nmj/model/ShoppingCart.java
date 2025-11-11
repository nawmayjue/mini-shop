package org.nmj.model;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public ShoppingCart() {}

    public ShoppingCart initialize(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity=quantity;
        return this;
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }


    public void setId(Long id) { this.id = id; }

    public void setProduct(Product product) { this.product = product; }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

