package com.example.demo.models;

public class Manager extends AbstractUser {
    
    public Manager(){
        super();
    }


    public Manager(String name,String email ,String password ,String phone_number){
        super(name, email, password, phone_number);
        
    }



}
