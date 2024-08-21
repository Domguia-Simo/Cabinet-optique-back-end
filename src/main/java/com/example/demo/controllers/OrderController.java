package com.example.demo.controllers;

import com.example.demo.models.Order;
import com.example.demo.models.OrderProduct;
import com.example.demo.services.OrderServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @PostMapping("/order-product/{userId}")
    public ResponseEntity<?> orderProduct(
            @PathVariable Long userId,
            @RequestBody Map<String ,?> data){

        List<Map> p = (List) data.get("products");
        String date = String.valueOf( data.get("date"));

        Map<String ,?> response = orderServiceImp.orderProduct((Long) userId ,(List<Map>) p ,date);
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

    @GetMapping("/get-merge-orders")
    public ResponseEntity<?> geMergeOrders(){
        return ResponseEntity.status(200).body(orderServiceImp.getMergeOrders());
    }

    @GetMapping("/get-user-orders/{userId}")
    public List<Order> getUserOrders(@PathVariable("userId") Long id){
        return orderServiceImp.getUserOrders(id);
    }

    @GetMapping("/get-order-details/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") Long orderId){
        return ResponseEntity.status(200).body(orderServiceImp.getOrderDetails(orderId));
    }

    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<?>  deleteOrder(@PathVariable("orderId") Long orderId){
        orderServiceImp.deleteOrder(orderId);
        return ResponseEntity.status(200).body("Order deleted successfully");
    }

}
