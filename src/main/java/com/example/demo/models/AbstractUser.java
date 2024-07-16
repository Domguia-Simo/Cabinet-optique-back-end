package com.example.demo.models;

public abstract class AbstractUser {
    protected Long id;
    protected String name;
    protected String email;
    protected String password;
    protected String phone_number;

    // Default constructor
    public AbstractUser(){

    }

    // Completely parametised constructor
    public AbstractUser(String name,String email ,String password ,String phone_number){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    // Partially parametised constructor
    public AbstractUser(String name,String email ,String phone_number){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    // Getters
    public long getid(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getPhoneNumber(){return phone_number;}

    // Setters
    public void setid(long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setPhoneNumber(String phone_number){this.phone_number = phone_number;}



}
