package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.models.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.UserInterface;


@Service
public class UserServiceImp implements UserInterface{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ConsultationRepository consultationRepository;

    private final BCryptPasswordEncoder bcrypt;
    public UserServiceImp() {
        bcrypt = new BCryptPasswordEncoder();
    }


    @Override
    public Map<String ,?> login(String email ,String password){
        Optional<User> Op = userRepository.findByEmail(email);
        if(Op.isPresent()){
            User u = Op.get();
            System.out.println(u.getPassword());
                boolean test = bcrypt.matches(password ,u.getPassword());
            if(test){

                List<Consultation> userConsultation = consultationRepository.findByUserId(u.getId());
                userConsultation.forEach(consultation -> {
//                    Consultation temp = consultation;
//                    temp.setUser(null);
//                    return temp;
                    consultation.setUser(null);
                });
                List<Order> userOrder = orderRepository.findUserOrder(u.getId());
//                List<List<OrderProduct>> userOrderProduct =new ArrayList<>();
                List<Map<String ,?>> userOrderProduct =new ArrayList<>();

                for(Order o:userOrder){
                    List<OrderProduct> temp = orderProductRepo.findOrderDetail(o.getId());
                    userOrderProduct.add(Map.of("date" ,o.getDate() ,"products" ,temp));
                }
                u.setPassword(null);

//                List<Order>
                return Map.of("success" ,  Map.of("user" ,u,"consultations" ,userConsultation ,"orders" ,userOrderProduct) );
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
