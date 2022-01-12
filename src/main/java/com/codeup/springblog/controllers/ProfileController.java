package com.codeup.springblog.controllers;

import com.codeup.springblog.EmailService;
import com.codeup.springblog.PostRepository;
import com.codeup.springblog.User;
import com.codeup.springblog.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public ProfileController(PostRepository postRepository, UserRepository userRepository, EmailService emailService) {  //name postRepository i.e. adDao or postDao
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
//    @GetMapping("/profile/{username}")
//    public String profile(@PathVariable String username, Model model){
//        model.addAttribute("username", username);
//        return "profile";
//    }

    @GetMapping("/profile")
    public String viewProfilePage(User userId, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userId = userRepository.getById(loggedInUser.getId());
        model.addAttribute("username", loggedInUser.getUsername());
        return "/profile";
    }

    //how to show a list of only this user's posts?
//    @GetMapping("/index")
//    public String postsIndex(Model model) {
//        model.addAttribute("posts", postRepository.findAll());
//        return "posts/index";
//    }


}
