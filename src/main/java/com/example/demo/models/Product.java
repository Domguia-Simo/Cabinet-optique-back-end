package com.example.demo.models;

public class Product {
    private long product_id;
    private String name;
    private String colour;
    private int size;
    private String type;
    private long price;

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

    // setters
    public void setName(String name){this.name = name;}
    public void setColour(String colour){this.colour = colour;}
    public void setSize(int size){this.size = size;}
    public void setType(String type){this.type = type;}
    public void setPrice(long price){this.price = price;}



}
