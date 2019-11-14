package com.shaneen.blog.blog.controllers;


import com.shaneen.blog.blog.form.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String index(Post post) {
        return "index";
    }

    @RequestMapping(value = "/", method=RequestMethod.POST)
    public String addNewPost(@Valid Post post, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());
        return "result";
    }
}
