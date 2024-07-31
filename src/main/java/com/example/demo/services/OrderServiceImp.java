package com.example.demo.services;

import com.example.demo.interfaces.OrderInterface;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String ,String> orderProduct(Long userId , Long productId , int quntity , Date data){

//        System.out.println(data);

//        Optional<User> Ou = userRepository.findById(userId);
//        Optional<Product> Op = productRepository.findById(productId);
//        if(Ou.isPresent()){
//            User u = Ou.get();
//            if(Op.isPresent()){
//                Product p = Op.get();
//
//            }else{
//              return Map.of("error" ,"The product does not exist");
//            }
//        }else{
//            return Map.of("error" ,"User does not exist");
//        }

        return null;
    }

    @Override
    public void deleteOrder(){

    }

    @Override
    public void getOrders(){

    }

}
