package com.hack.app.controller;

import com.hack.app.entity.User;
import com.hack.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    public UserController(UserService service){this.service = service;}


    @PostMapping(value = "/update/{login}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> update(@PathVariable("login") String login, @RequestBody User user){
        try{
            service.update(user.getName(), user.getInfo(), user.getStatus(), login);
        }catch (UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{login}")
    public ResponseEntity<User> findUserByLogin(@PathVariable("login") String login){
        try {
            User user = service.findUserByLogin(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
