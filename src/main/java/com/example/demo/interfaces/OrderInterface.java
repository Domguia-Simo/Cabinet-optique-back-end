package com.example.demo.interfaces;

import com.example.demo.models.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderInterface {
    public Map<String ,?> orderProduct(Long userId , Long productId , int quntity , String data);
    public void deleteOrder();
    public List<Order> getOrders();
}
