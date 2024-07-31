package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String email;
    protected String password;
    protected String phone_number;


//    @OneToMany(mappedBy = "user")
//    private List<Order> orders;

    // Default constructor
    public User(){

    }

    // Completely parametised constructor
    public User(String name,String email ,String password ,String phone_number){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    // Partially parametised constructor
    public User(String name,String email ,String phone_number){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    // Getters
    public long getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getPhoneNumber(){return phone_number;}

    // Setters
    public void setId(long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setPhoneNumber(String phone_number){this.phone_number = phone_number;}



}
