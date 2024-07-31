package com.example.demo.interfaces;

import java.util.Date;
import java.util.Map;

public interface OrderInterface {
    public Map<String ,String> orderProduct(Long userId , Long productId , int quntity , Date data);
    public void deleteOrder();
    public void getOrders();
}
