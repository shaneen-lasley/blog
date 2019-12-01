package com.shaneen.blog.blog.controllers;

import com.shaneen.blog.blog.models.Post;
import com.shaneen.blog.blog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController extends AbstractController {

    @RequestMapping(value = "/")
    public String index(Model model){

        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/blog")
    public String blogIndex(Model model) {

        List<Post> posts = (List<Post>) postDao.findAll();
        model.addAttribute("posts", posts);

        return "blog";
    }

}
