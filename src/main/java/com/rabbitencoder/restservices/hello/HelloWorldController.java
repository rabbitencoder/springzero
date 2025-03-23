package com.rabbitencoder.restservices.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author rahul
 * @date 5/16/2024 11:18 AM
 * -
 */
//Controller
@RestController
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private ResourceBundleMessageSource messageSource;
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

    @GetMapping("/hello-int")
    public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required=false) String locale){
        return messageSource.getMessage("label.hello", null, new Locale(locale));
    }

    @GetMapping("/hello-int2")
    public String getMessagesInI18NFormat(){
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
    }


}

    