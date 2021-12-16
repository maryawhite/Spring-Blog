package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody

    public String hello(){
        return "Hello from Mary!";
    }

    @GetMapping("/hello{name}") //name is a variable that we named name
    @ResponseBody
    public String sayHello(@PathVariable String name){
        return "Hello there " + name + "!";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number){
        return number + " plus one is " + (number + 1) + ".";
    }


}
