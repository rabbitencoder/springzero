package com.rabbitencoder.restservices.controllers;

import com.rabbitencoder.restservices.entities.Order;
import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.exceptions.UserNotFoundException;
import com.rabbitencoder.restservices.repositories.OrderRepository;
import com.rabbitencoder.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author rahul
 * @date 3/7/2025 9:46 AM
 * -
 */

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public CollectionModel<EntityModel<Order>> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        List<Order> allOrders =  user.get().getOrders();
        List<EntityModel<Order>> orderResources = new ArrayList<>();

        for (Order order : allOrders){
            EntityModel<Order> orderEntityModel = EntityModel.of(order);
            Long orderId  = order.getOrderId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(orderId).withSelfRel();
            orderEntityModel.add(selfLink);
            orderResources.add(orderEntityModel);
        }
        Link userOrdersLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrders(userId)
        ).withSelfRel();

        return CollectionModel.of(orderResources, userOrdersLink);

    }
}

    