package com.example.demo.services;

import com.example.demo.models.Consultation;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticServiceImp {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepo orderProductRepo;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<?> getMostOrderProduct(){
        List<Integer> pId = orderProductRepo.findProductFrequency();
            List<Product> ps = new ArrayList<>();
        for(int i=0;i<pId.size();i++){
                Optional<Product> temp = productRepository.findById(Long.valueOf(pId.get(i)));
                if(temp.isPresent()){ ps.add(temp.get()); }
            }
        return ps;
    }

    public Map<String ,List> getMostActiveUser(){
        Map<String,List> active = new HashMap<>();
        List<?> active_user_by_order = orderRepository.findActiveUserByOrder();
        List<?> active_user_by_consultation = consultationRepository.findActiveUserByConsultation();

        List<User> us = new ArrayList<>();
        for(int i=0;i<active_user_by_order.size();i++){
            Optional<User> tempU = userRepository.findById((Long) active_user_by_order.get(i));
            if(tempU.isPresent()){ us.add(tempU.get()); }
        }
        List<User> cs = new ArrayList<>();
        for(int j=0;j<active_user_by_consultation.size();j++){
            Optional<User> tempC = userRepository.findById((Long) active_user_by_consultation.get(j));
            if(tempC.isPresent()){ cs.add(tempC.get()); }
        }

         active.put("order", us);
         active.put("consultation" ,cs);

        return active;
    }

    public List<?> getMostRequestedDate(){
        return List.of();
    }


}
