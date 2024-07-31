package com.example.demo.controllers;

import com.example.demo.services.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @PostMapping("/order-product/{userId}/{productId}")
    public ResponseEntity<?> orderProduct(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody Map<String ,?> data){

        System.out.println(data);
        Integer quantity = (Integer)data.get("quantity");
        String date = (String) data.get("date");

        Map<String ,?> response = orderServiceImp.orderProduct(userId ,productId ,quantity ,date);
        if(response.get("success") != null){
            return ResponseEntity.status(200).body(response.get("success"));
        }else{
            return ResponseEntity.status(401).body(response.get("error"));
        }
    }

    @GetMapping("/get-order")
    public ResponseEntity<?> getOrders(){
        return ResponseEntity.status(200).body(orderServiceImp.getOrders());
    }

}
