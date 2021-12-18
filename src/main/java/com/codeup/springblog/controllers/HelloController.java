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
        return "Hello from Mary!";  //this is what displays when you visit /home
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


    @GetMapping("/roll-dice")
    public String showDiceForm() {
        return "roll-dice";  //it looks like the return returns the html of that name
    }

    @PostMapping("/roll-dice")
    public String userGuess(@RequestParam(name = "userGuess") String userGuess, Model model) {
        Random random = new Random();
        int int_random = random.nextInt(6) + 1;
        model.addAttribute("userGuess", "You Guessed " + userGuess);
        model.addAttribute("int_random", "The random number is " + int_random);  //this isn't showing up on the page...
        return "roll-dice";

    }


}
