package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity
// @Table(name="clients")
public class Client extends User{


    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;


    public Client(){
        super();
    }

    public Client(String name,String email ,String password ,String phone_number){
        super(name, email, password, phone_number);
        
    }

    
}
