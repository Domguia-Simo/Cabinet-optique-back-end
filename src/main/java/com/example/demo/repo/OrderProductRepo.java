package com.example.demo.repo;

import com.example.demo.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct ,Long> {

    @Query(value = "select * from order_product where order_id=?1" ,nativeQuery = true)
    public List<OrderProduct> findOrderDetail(long orderId);

    void deleteByOrderId(Long orderId);

//    @Query(value = "delete from order_product where order_id=?1", nativeQuery = true)
//    public List<OrderProduct> deleteByOrderId(long orderId);

}
