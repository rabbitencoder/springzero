package com.rabbitencoder.restservices.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * @author rahul
 * @date 6/5/2024 5:35 PM
 * -
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Id", length = 15, nullable = false)
    private Long orderId;

    @Column(name = "order_Description", length = 15, nullable = false)
    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)  // Correct annotation for ManyToOne relationship
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
    @JsonIgnore
    private User user;

    public Order() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

    