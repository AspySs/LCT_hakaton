package com.hack.client.controller;

import com.hack.client.entity.Ideas;
import com.hack.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.hack.client.utils.Utils.getRequest;
import static com.hack.client.utils.Utils.postRequest;

@Controller
public class UserController {

    @Autowired
    private User user;

    @ExceptionHandler({HttpClientErrorException.Unauthorized.class, HttpClientErrorException.Forbidden.class})
    public String handleException(HttpClientErrorException e, Model model) {
        if (HttpStatus.FORBIDDEN.equals(e.getStatusCode())) {
            model.addAttribute("message", "Not enough rights for this (need to be admin)");
        }
        if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("token", user.getToken());
        return "signin/authorizationEx";
    }

    @GetMapping("/user/update/{login}")
    public String getUpdatePage(@PathVariable("login") String login, Model model) {
        model.addAttribute("title", "User info Update");
        model.addAttribute("user", user);
        model.addAttribute("token", user.getToken());
        return "user/updUser";
    }

    @PostMapping("/user/update/{login}")
    public String updateUsr(@PathVariable("login") String login, @RequestParam("newName") String nName,
                            @RequestParam("newInfo") String nInfo, @RequestParam("newStatus") String nStatus,
                            Model model) {
        model.addAttribute("title", "User info Update");
        String url = "http://localhost:8081/user/update/" + login;
        String json = "{\n" +
                "  \"name\": \""+nName+"\",\n" +
                "  \"info\": \""+nInfo+"\",\n" +
                "  \"status\": \""+nStatus+"\",\n" +
                "  \"username\": \""+user.getLogin()+"\"\n" +
                "}";
        postRequest(url, user.getToken(), json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/account";
    }
}
