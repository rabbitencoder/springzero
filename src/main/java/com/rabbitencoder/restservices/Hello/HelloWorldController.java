package com.rabbitencoder.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rahul
 * @date 5/16/2024 11:18 AM
 * -
 */
//Controller
@RestController
public class HelloWorldController {
    //URI - /helloworld
    //@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("helloworld1")
    public String hellowWorld(){
        return "Hello World1";
    }

    @GetMapping("/helloworldBean")
    public UserDetails helloWorldBean(){
        return new UserDetails("rahul", "singh", "xyz");
    }
}

    