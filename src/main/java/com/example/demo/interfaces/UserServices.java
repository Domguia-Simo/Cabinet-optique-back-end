package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.models.Client;

public interface UserServices {

    public void login();
    public void register();

    public List<Client> getClients();
    public void updateClient();
    public void deleteClient();

    // public void orderProduct();
    // public void requestConsultation();
    // public void contact();

}
