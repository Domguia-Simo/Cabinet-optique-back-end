package com.example.demo.models;

public class Client extends AbstractUser{

    public Client(){
        super();
    }


    public Client(String name,String email ,String password ,String phone_number){
        super(name, email, password, phone_number);
        
    }

    
}
