package com.example.demo.interfaces;

import java.util.List;
import java.util.Map;


// import org.springframework.stereotype.Service;

import com.example.demo.models.Client;
import com.example.demo.models.User;

public interface UserInterface {

    public Map<String ,?> login(String email ,String password);
    public void register(User user);

    public List<User> getClients();
    public void updateClient();
    public void deleteClient();

//     public Map<String ,String> orderProduct(Long userId , Long productId);

    // public void requestConsultation();
    // public void contact();

}
