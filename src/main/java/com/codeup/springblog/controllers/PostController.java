package com.codeup.springblog.controllers;

import com.codeup.springblog.Post;
import com.codeup.springblog.PostRepository;
import com.codeup.springblog.User;
import com.codeup.springblog.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class PostController {
    //dependency injection, create a Repository instance and initialize it in the controller class constructor
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {  //name postRepository i.e. adDao or postDao
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
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
    @GetMapping("/posts/{postId}")
    public String showPost(Model model, @PathVariable Long postId, Long id) {
        Post showPost = postRepository.getById(postId);
        model.addAttribute("post", showPost);
        model.addAttribute("user", showPost.getUser()); //what is this doing? it's going into the Post class and using the getter that I created after I added the ManytoOne relationship
        return "/posts/show";
    }
//    @GetMapping("/posts/show")
//    public String showPost(Model model){
//        //views exercise
//        Post onePost1 = new Post("A Post", "The body of my post");
//        model.addAttribute("onepost", onePost1);
//        return "/posts/show";
//    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model){
        model.addAttribute("post", new Post());  //adding this for form model binding
        return "/posts/create";
    }

//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST) //or use @Postmapping
//    @ResponseBody
//    public String createPostForm(){
//        return "this will be the com.codeup.springblog.Post method to create a new post";
//    }

    @PostMapping("/posts/create")
    public String addNewPost(@ModelAttribute Post post) {  //this is @ModelAttribute, NOT @RequestBody
        //when creating a post, before saving, assign a user to it.
        User user = userRepository.getById(1L);
        //add a setter in Post to set the user...
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/index";
    }

    //edit functionality
    @GetMapping("/edit/{postId}")
    public String viewPost(Model model, @PathVariable Long postId) {
        Post post = postRepository.getById(postId);
        model.addAttribute("posts", post); //this pre-populates the info in the form in the edit.html
        return "/posts/edit";
    }

    @PostMapping("/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId, @ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/index";
    }

    //delete functionality--add a delete button in the show.html
    @PostMapping("/posts/index")
    public String deletePost(Long postId) {
        postRepository.deleteById(postId);
        return "redirect:/index";
    }


}
