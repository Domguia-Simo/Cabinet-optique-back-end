package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.UserInterface;
import com.example.demo.models.User;
import com.example.demo.models.Client;
import com.example.demo.repo.UserRepository;


@Service
public class UserServiceImp implements UserInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email ,String password){
        Optional<User> Op = userRepository.findByEmail(email);
        // System.out.print(Op.isEmpty() ? "it is empty":"It is not empty");
        if(Op.isPresent()){
            User u = Op.get();
            // System.out.print(u.getPhoneNumber());
            // System.out.print(u.getEmail());
            // System.out.print(u.getPassword());
            // System.out.print(u.getName());
            // System.out.print(u.getId());
            
            if(u.getPassword().equals(password)){
                return "Login successfull";
            }else{
                return "Invalid password";
            }

        }else{
            return "Not existing email";
        }
    }

    
    @Override
    public void register(User user){
        
        userRepository.save(user);
    }


    @Override
    public List<User> getClients(){return List.of();}

    @Override
    public void updateClient(){}

    @Override
    public void deleteClient(){}
    
}
