package com.example.demo.repo;

import com.example.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select * from orders where user_id=?1" ,nativeQuery = true)
    public List<Order> findUserOrder(Long id);

//    Testing joins with other tables
    @Query(value = "select * from orders inner join order_product on orders.id = order_product.order_id inner join products on products.product_id = order_product.product_id" ,nativeQuery = true)
    public List<?> findMergeOrder();

//Query to get most active user based on orders
    @Query(value = "select user_id from orders group by user_id order by count(*) desc limit 3" ,nativeQuery = true)
    public List<?> findActiveUserByOrder();

}
