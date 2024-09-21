package com.example.demo.repo;

import com.example.demo.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct ,Long> {

    @Query(value = "select * from order_product where order_id=?1" ,nativeQuery = true)
    public List<OrderProduct> findOrderDetail(long orderId);

    void deleteByOrderId(Long orderId);

//    @Query(value = "delete from order_product where order_id=?1", nativeQuery = true)
//    public List<OrderProduct> deleteByOrderId(long orderId);

    //    Query to get the list of product and their frequency of ordering
    @Query(value = "select product_id from order_product group by product_id order by count(*) limit 3 " ,nativeQuery = true )
    public List<Integer> findProductFrequency();

}
