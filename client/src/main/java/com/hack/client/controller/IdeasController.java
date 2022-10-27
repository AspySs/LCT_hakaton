package com.hack.client.controller;

import com.hack.client.entity.Comments;
import com.hack.client.entity.Ideas;
import com.hack.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.hack.client.utils.Utils.getRequest;

@Controller
public class IdeasController {
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

    @GetMapping("/ideas")
    public String getIdeasPage(Model model) {
        model.addAttribute("title", "Ideas");

        String url = "http://localhost:8081/ideas/find/all";
        List<Ideas> ideas = getRequest(url, List.class);
        model.addAttribute("ideas", ideas);
        model.addAttribute("token", user.getToken());
        return "ideas/ideas";
    }

    @GetMapping("/ideas/find/byId/{id}")
    public String getChargePage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("title", "Idea №" + id);

        String url = "http://localhost:8081/ideas/find/byId/" + id;
        String url_react_1 = "http://localhost:8081/reactions/count/idea/true/" + id;
        String url_react_0 = "http://localhost:8081/reactions/count/idea/false/" + id;
        String url_comments = "http://localhost:8081/comments/find/idea/" + id;

        Ideas idea = getRequest(url, Ideas.class);
        Long reaction_0 = getRequest(url_react_0, Long.class);
        Long reaction_1 = getRequest(url_react_1, Long.class);
        List<Comments> comms = getRequest(url_comments, List.class);
        model.addAttribute("idea", idea);
        model.addAttribute("reaction_0", reaction_0);
        model.addAttribute("reaction_1", reaction_1);
        model.addAttribute("comms", comms);
        model.addAttribute("token", user.getToken());

        return "ideas/idea";
    }

}
