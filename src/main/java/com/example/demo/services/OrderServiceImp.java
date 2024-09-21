package com.example.demo.services;

import com.example.demo.interfaces.OrderInterface;
import com.example.demo.models.Order;
import com.example.demo.models.OrderProduct;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repo.OrderProductRepo;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImp implements OrderInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Override
    public Map<String ,?> orderProduct(Long userId ,List<Map> products , String date){

        Optional<User> Ou = userRepository.findById(userId);
        if(Ou.isPresent()) {
            User u = Ou.get();

            Order newOrder = new Order();
            newOrder.setUser(u);
            newOrder.setDate(date);
            Order response = orderRepository.save(newOrder);

            for(Map item:products){
                Long productId = Long.valueOf(((Integer)item.get("productId")).intValue()) ;
                long quantity = (long) ((Number)item.get("quantity")).intValue();

                Optional<Product> Op = productRepository.findById(productId);
                if(Op.isPresent()){
                    Product p = Op.get();

                    long total_price = quantity * p.getPrice();

                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct(p);
                    orderProduct.setOrder(response);
                    orderProduct.setQuantity(quantity);
                    orderProduct.setPrice(total_price);

                    orderProductRepo.save(orderProduct);

                }else{
                    return Map.of("error" ,"Invalid product id (it does not exist) ");
                }
            }
//If you reach here therefore every thing happened successfully
            return Map.of("success" , "Order placed successfully");
        }
        else{
            return Map.of("error" ,"User does not exist");
        }
    }

    @Override
    public void deleteOrder(Long orderId){
        orderProductRepo.deleteByOrderId(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public List<?> getMergeOrders(){
        return orderRepository.findMergeOrder();
    }

    @Override
    public List<Map<String ,?>> getUserOrders(Long id){
//        return orderRepository.findUserOrder(id);

        List<Order> userOrder = orderRepository.findUserOrder(id);
        List<Map<String ,?>> userOrderProduct =new ArrayList<>();

        for(Order o:userOrder){
            List<OrderProduct> temp = orderProductRepo.findOrderDetail(o.getId());
            userOrderProduct.add(Map.of("date" ,o.getDate() ,"products" ,temp));
        }

        return userOrderProduct;

    }

    @Override
    public List<OrderProduct> getOrderDetails(Long id){
        return orderProductRepo.findOrderDetail(id);
    }

}
