package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.models.Product;
import com.example.demo.repo.ConsultationRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.UserInterface;
import com.example.demo.models.User;
import com.example.demo.models.Client;
import com.example.demo.repo.UserRepository;


@Service
public class UserServiceImp implements UserInterface{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ConsultationRepository consultationRepository;

    private final BCryptPasswordEncoder bcrypt;
    public UserServiceImp() {
        bcrypt = new BCryptPasswordEncoder();
    }


    @Override
    public Map<String ,String> login(String email ,String password){
        Optional<User> Op = userRepository.findByEmail(email);
        if(Op.isPresent()){
            User u = Op.get();
            System.out.println(u.getPassword());
                boolean test = bcrypt.matches(password ,u.getPassword());
            if(test){
                return Map.of("success" ,"Login successfull id "+u.getId());
            }else{
                return  Map.of("error" ,"Invalid password") ;
            }
        }else{
            return Map.of("error" , "No existing email") ;
        }
    }

    
    @Override
    public void register(User user){

//        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        String hashPassword = bcrypt.encode(user.getPassword());
        user.setPassword(hashPassword);

        userRepository.save(user);
    }


    @Override
    public List<User> getClients(){return userRepository.findAll();}

    @Override
    public void updateClient(){}

    @Override
    public void deleteClient(){}

    public Map<?,?> getSummary(){
        long users = userRepository.count();
        long products = productRepository.count();
        long consultations = consultationRepository.count();
        long orders = orderRepository.count();

        return Map.of("users" ,users ,"products" ,products ,"consultations" ,consultations ,"orders",orders ,"messages" ,0);
    }
    
}
