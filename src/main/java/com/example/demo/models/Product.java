package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    private String name;
    private String colour;
    private int size;
    private String type;
    private long price;

    private String image;

    public Product(){

    }

    public Product(String name ,String colour ,int size ,String type ,long price){
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.type = type;
        this.price = price;
    }

    // getters
    public long getId(){return product_id;}
    public String getName(){return name;}
    public String getColour(){return colour;}
    public int getSize(){return size;}
    public String getType(){return type;}
    public long getPrice(){return price;}
    public String getImage(){return image;}

    // setters
    public void setName(String name){this.name = name;}
    public void setColour(String colour){this.colour = colour;}
    public void setSize(int size){this.size = size;}
    public void setType(String type){this.type = type;}
    public void setPrice(long price){this.price = price;}
    public void setImage(String image){this.image = image;}



}
