package com.rabbitencoder.restservices.repositories;

import com.rabbitencoder.restservices.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rahul
 * @date 3/4/2025 6:26 AM
 * -
 */

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {
    Order getOrderByOrderId(Long orderId);
}
