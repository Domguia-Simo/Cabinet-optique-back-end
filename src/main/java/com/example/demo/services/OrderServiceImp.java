package com.example.demo.services;

import com.example.demo.interfaces.OrderInterface;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String ,?> orderProduct(Long userId , Long productId , int quantity , String date){

        Optional<User> Ou = userRepository.findById(userId);
        Optional<Product> Op = productRepository.findById(productId);
        if(Ou.isPresent()){
            User u = Ou.get();
            if(Op.isPresent()){
                Product p = Op.get();
                Order newOrder = new Order();
                newOrder.setProduct(p);
                newOrder.setUser(u);
                newOrder.setDate(date);
                newOrder.setQuantity(quantity);
                Order response = orderRepository.save(newOrder);

                return Map.of("success" ,response);

            }else{
              return Map.of("error" ,"The product does not exist");
            }
        }else{
            return Map.of("error" ,"User does not exist");
        }

    }

    @Override
    public void deleteOrder(){

    }

    @Override
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

}
