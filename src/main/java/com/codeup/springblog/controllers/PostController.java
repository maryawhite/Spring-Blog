package com.codeup.springblog.controllers;

import com.codeup.springblog.Post;
import com.codeup.springblog.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PostController {
    //dependency injection, create a Repository instance and initialize it in the controller class constructor
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {  //name postRepository i.e. adDao or postDao
        this.postRepository = postRepository;
    }

    @GetMapping("/posts/index")
    public String postsIndex(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/index";
    }


//    @RequestMapping(path = "/posts/index", method = RequestMethod.GET)   //or use @GetMapping("/posts/index")  UPDATED above after adding PostRepository
//    public String postsIndex(Model model){
//        ArrayList<Post> posts = new ArrayList<>();
//        Post post1 = new Post("title1", "body1");
//        Post post2 = new Post("title2", "body2");
//        posts.add(post1);
//        posts.add(post2);
//        model.addAttribute("posts", posts); //posts is the name of the arrayList, now we can iterate through posts on the index.html
//        return "/posts/index";
//    }

//    @RequestMapping(path= "/posts/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String individualPost(@PathVariable int id){
//        return "this will be where you view an individual post by id" + id;
//    }

    @GetMapping("/posts/show")
    public String showPost(Model model){
        //views exercise
        Post onePost1 = new Post("A Post", "The body of my post");
        model.addAttribute("onepost", onePost1);
        return "/posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    @ResponseBody
    public String viewCreatePost(){
//        return "This is where you view the form for creating a post";
        return "/posts/create";
    }

//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST) //or use @Postmapping
//    @ResponseBody
//    public String createPostForm(){
//        return "this will be the com.codeup.springblog.Post method to create a new post";
//    }

    @PostMapping("/posts/create")
    public String addNewPost(@ModelAttribute Post post) {  //this is @ModelAttribute, NOT @RequestBody
        postRepository.save(post);
        return "redirect:/posts/index";
    }

    //add edit functionality
    @PostMapping("/posts/{postId}/edit")
    public void editPost(@PathVariable("postId") Long postId, String title, String body) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("post with id " + postId + " not found."));

        if(title != null && title.length() > 0 && !Objects.equals(post.getTitle(), title)) {
            post.setTitle(title);
        }
        if(body != null && body.length() > 0 && !Objects.equals(post.getBody(), body)) {
            post.setBody(body);
        }
    }


    //add delete functionality--add a delete button in the show.html
//    @DeleteMapping(path = "{postId}")
    @PostMapping("/posts/index")
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }



}
