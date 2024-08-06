package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long price;
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderProduct(){ }

    public OrderProduct(long price ,long quantity){
        this.quantity = quantity;
        this.price = price;
    }


//    Setters
    public void setId(Long id){this.id = id;}
    public void setQuantity(long quantity){this.quantity = quantity;}
    public void setPrice(long price){this.price = price;}
    public void setProduct(Product p){this.product = p;}
    public void setOrder(Order o){this.order = o;}


//    Getters
    public Long getId(){return id;}
    public long getQuantity(){return quantity;}
    public long getPrice(){return price;}
    public Product getProduct(){return product;}
    public Order getOrder(){return order;}
}
