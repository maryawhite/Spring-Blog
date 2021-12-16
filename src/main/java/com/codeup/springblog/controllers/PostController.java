package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String postsIndex(){
        return "this will be the posts index page";
    }

    @RequestMapping(path= "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postId(@PathVariable int id){
        return "this will be where you view an individual post by id";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPost(){
        return "This will be where you view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPostForm(){
        return "this will be the Post method to create a new post";
    }

}
