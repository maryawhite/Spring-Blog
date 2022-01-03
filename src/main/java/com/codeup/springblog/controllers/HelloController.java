package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody

    public String hello(){
        return "Hello from Mary!";  //this is what displays when you visit /hello
    }

//    @GetMapping("/hello{name}") //name is a variable that we named name
//    @ResponseBody
//    public String sayHello(@PathVariable String name){
//        return "Hello there " + name + "!";
//    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number){
        return number + " plus one is " + (number + 1) + ".";
    }

    //---views---
    @GetMapping("/hello/{name}")  //now if we visit /hello/Codeup, the page will fill in Codeup as the name in the hello.html
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }

    @PostMapping ("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {  //the html input field has a name of cohort.
        model.addAttribute("cohort", "Welcome to " + cohort + "!");  //after you submit the form, it will display this string
        return "join";
    }
}
