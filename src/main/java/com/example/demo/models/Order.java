package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Order(){}

    public Order(String date ,int quantity ){
        this.date = date;
        this.quantity = quantity;
    }

//    Setters
    public void setId(Long id){this.id = id;}
    public void setDate(String date){this.date = date;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public void setUser(User u){this.user = u;}
    public void setProduct(Product p){this.product = p;}

//    Getters
    public Long getId(){return this.id;}
    public String getDate(){return date;}
    public int getQuantity(){return quantity;}
    public User getUser(){return user;}
    public Product getProduct(){return product;}

}
