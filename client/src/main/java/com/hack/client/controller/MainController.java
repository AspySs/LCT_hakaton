package com.hack.client.controller;


import com.hack.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private User user;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Homepage");
        model.addAttribute("token", user.getToken());
        return "index";
    }

    @GetMapping("/account")
    public String getAccountPage(Model model) {
        model.addAttribute("title", "Account");
        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/logout")
    public String logOut(Model model) {
        user.setLogin(null);
        user.setToken(null);
        model.addAttribute("title", "Login");
        model.addAttribute("token", user.getToken());
        return "signin/login";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
