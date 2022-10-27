package com.hack.client.controller.signin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hack.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static com.hack.client.utils.Utils.authRequest;

@Controller
public class LoginController {
    @Autowired
    private User user;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("title", "Login");
        return "signin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        String url = "http://localhost:8081/auth/login";
        String json = "{\n" +
                "\"login\":" + "\"" + login + "\"" + ",\n" +
                "\"password\":" + "\"" + password + "\"" + "\n" +
                "}";
        ResponseEntity<String> responseEntity = authRequest(url, json,
                HttpMethod.POST, MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User tempUser = objectMapper.readValue(responseEntity.getBody(), User.class);
            user.setLogin(tempUser.getLogin());
            user.setToken(tempUser.getToken());
        } catch (JsonProcessingException error) {
            System.err.println(error.getMessage());
        }

        return "redirect:/account";
    }

}