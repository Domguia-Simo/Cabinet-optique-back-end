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
    public void deleteOrder(){

    }

    @Override
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getUserOrders(Long id){
        return orderRepository.findUserOrder(id);
    }

}
