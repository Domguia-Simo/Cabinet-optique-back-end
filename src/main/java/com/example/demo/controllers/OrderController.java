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
            @RequestBody Map<String ,Object> data){

        System.out.println(data);
        Integer quantity = (Integer)data.get("quantity");
        Date date = (Date) data.get("date");

        Map<String ,String> repsonse = orderServiceImp.orderProduct(userId ,productId ,quantity ,date);
        return ResponseEntity.status(200).body("Order registered succesfully");
    }


}
