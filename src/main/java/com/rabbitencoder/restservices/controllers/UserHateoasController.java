package com.rabbitencoder.restservices.controllers;

import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.exceptions.UserNotFoundException;
import com.rabbitencoder.restservices.repositories.UserRepository;
import com.rabbitencoder.restservices.services.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.ControllerEntityLinks;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author rahul
 * @date 3/7/2025 9:43 AM
 * -
 */

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUser() throws UserNotFoundException {

        List<User>  userList = userService.getAllUsers();
        List<EntityModel<User>> entityModelList = new ArrayList<>();

        for (User user : userList){
            EntityModel<User> userModel = EntityModel.of(user);
//            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
//                    this.getClass()).getUserById(user.getId())).withSelfRel();
            Long userId = user.getId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            userModel.add(selfLink);

            Link allorderLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId)).withRel("all-orders");
            userModel.add(allorderLink);
            entityModelList.add(userModel);
        }

        Link allUserLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser()).withSelfRel();


        return CollectionModel.of(entityModelList, allUserLink);

    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> userOptional =  userService.getUserById(id);
            User user = userOptional.get();
            Long userId = user.getId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);
            EntityModel<User> finalResource = EntityModel.of(user);
            return finalResource;
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}

    