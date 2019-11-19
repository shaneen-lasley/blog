package com.shaneen.blog.blog.controllers;


import com.shaneen.blog.blog.forms.LoginForm;
import com.shaneen.blog.blog.services.NotificationService;
import com.shaneen.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/users/login")
    public String login(LoginForm loginForm) {
        return "users/login";
    }
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill out form correctly!");
            return "users/login";
        }
        if (!userService.authenticate(loginForm.getUserName(), loginForm.getPassword())) {
            notifyService.addErrorMessage("Invalid Login!");
            return "users/login";
        }
        notifyService.addInfoMessage("Login successful");
        return "redirect:/";
    }
}
