package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//public class HomeController {
//    @GetMapping("/")
//    @ResponseBody
//    public String hello(){
//        return "This is the landing page!";
//    }
//}

//to use views, we will remove the responseBody annotation and create a home.html
@Controller
public class HomeController {
    @GetMapping("/home") //the name needs to match the name of the html file in your resources/templates folder
    public String welcome(){
        return "home";
    }
}
