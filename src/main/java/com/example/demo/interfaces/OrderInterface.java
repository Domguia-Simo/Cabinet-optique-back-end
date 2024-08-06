package com.example.demo.interfaces;

import com.example.demo.models.Order;
import com.example.demo.models.OrderProduct;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderInterface {
    public Map<String ,?> orderProduct(Long userId , List<Map> products , String data);
    public void deleteOrder(Long id);
    public List<Order> getOrders();
    public List<Order> getUserOrders(Long id);

    public List<OrderProduct> getOrderDetails(Long id);
}
