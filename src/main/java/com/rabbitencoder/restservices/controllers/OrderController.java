package com.rabbitencoder.restservices.controllers;

import com.rabbitencoder.restservices.entities.Order;
import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.exceptions.UserNotFoundException;
import com.rabbitencoder.restservices.repositories.OrderRepository;
import com.rabbitencoder.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author rahul
 * @date 2/27/2025 2:16 PM
 * -
 */

@RestController
@RequestMapping("/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        return user.get().getOrders();

    }

    //Create Order
    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("user not found");
        }

        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);

    }

    @GetMapping("/{userId}/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId, @PathVariable Long userId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new Exception("userId : " + user + " not found");
        }

        if (!order.isPresent()) {
            throw new Exception("OrderId : " + orderId + " not found");
        }

        return orderRepository.getOrderByOrderId(orderId);
    }
}

    