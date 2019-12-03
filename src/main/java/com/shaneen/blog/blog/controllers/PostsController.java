package com.shaneen.blog.blog.controllers;

import com.shaneen.blog.blog.models.Post;
import com.shaneen.blog.blog.models.User;
import com.shaneen.blog.blog.services.NotificationService;
import com.shaneen.blog.blog.services.PostService;
import com.shaneen.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private NotificationService notifyService;
    @Autowired
    private UserService userService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Post post = this.postService.findById(id);
        if( post == null ){
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        // To have something like src/main/resources/templates/<CONTROLLER-NAME>/<Mapping-Name-view>
        return "posts/view";
    }

    @RequestMapping("/posts/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject(post);
        modelAndView.setViewName("posts/create");
        return modelAndView;
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid Post post, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts/create");

        if( post.getTitle().isEmpty() ){
            bindingResult.rejectValue("title", "error.post", "Title cannot be empty");
        }
        if( post.getBody().isEmpty() ){
            bindingResult.rejectValue("body", "error.post", "Content cannot be empty");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUserName(auth.getName());
        if( user==null ){
            bindingResult.rejectValue("author", "error.post", "Author cannot be null");
        }
        if( !bindingResult.hasErrors() ){
            post.setAuthor(user);
            this.postService.create(post);
            modelAndView.addObject("successMessage", "Post has been created");
            modelAndView.addObject("post", new Post());
        }
        return modelAndView;
    }

    @RequestMapping("/posts/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Post post = this.postService.findById(id);
        if( post==null ){
            notifyService.addErrorMessage("Cannot find post #" + id);
        } else {
            this.postService.deleteById(id);
        }
        return "redirect:/posts/";
    }

    @RequestMapping( "/posts/edit/{id}" )
    public String edit(@PathVariable("id") Long id, Model model){
        Post post = this.postService.findById(id);
        if( post == null ){
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/posts/";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @RequestMapping(value = "/posts/edit", method = RequestMethod.POST )
    public ModelAndView edit(@Valid Post post, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts/edit");

        if( post.getTitle().isEmpty() ){
            bindingResult.rejectValue("title", "error.post", "Title cannot be empty");
        }
        if( post.getBody().isEmpty() ){
            bindingResult.rejectValue("body", "error.post", "Content cannot be empty");
        }

        User user = this.userService.findById(post.getAuthor().getId());
        if( user==null ){
            bindingResult.rejectValue("author", "error.post", "Author cannot be null");
        }
        if( !bindingResult.hasErrors() ){
            post.setAuthor(user);
            this.postService.create(post);
            modelAndView.addObject("successMessage", "Post has been updated");
            modelAndView.addObject("post", post);
        }
        return modelAndView;
    }

    @RequestMapping("/posts")
    public String index(Model model, @PageableDefault(sort = {"id"}, value = 3) Pageable pageable){

        Page<Post> posts = this.postService.findAll(pageable);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

}
