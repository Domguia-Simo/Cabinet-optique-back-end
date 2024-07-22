package com.example.demo.interfaces;

import java.util.List;


// import org.springframework.stereotype.Service;

import com.example.demo.models.Client;
import com.example.demo.models.User;

public interface UserInterface {

    public String login(String email ,String password);
    public void register(User user);

    public List<User> getClients();
    public void updateClient();
    public void deleteClient();

    // public void orderProduct();
    // public void requestConsultation();
    // public void contact();

}
