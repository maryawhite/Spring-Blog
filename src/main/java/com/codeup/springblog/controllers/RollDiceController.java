package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String showDiceForm() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{userGuess}") //note this is GetMapping
    public String userGuess(@PathVariable int userGuess, Model model) {  //a pathVariable is a variable that is part of the URI request, as opposed to being passed as a query string
        Random random = new Random();
        int int_random = random.nextInt(6) + 1;
        model.addAttribute("userGuess", "You selected " + userGuess);
        model.addAttribute("int_random", "The random number is " + int_random);
        int roll2 = random.nextInt(6) + 1;
        int roll3 = random.nextInt(6) + 1;
        model.addAttribute("roll2", "roll two: " + roll2 + ", ");
        model.addAttribute("roll3", "roll three: " + roll3);
        int counter = 0;

        if(userGuess == int_random){
            model.addAttribute("message", "You guessed correctly!");
            counter++;
        } else {
            model.addAttribute("message", "Sorry, not a match.");
        }
        if(userGuess == roll2){
            counter++;
        }
        if(userGuess == roll3){
            counter++;
        }
        model.addAttribute("counter", "number of matches = " + counter);
        return "roll-dice";
    }
}
