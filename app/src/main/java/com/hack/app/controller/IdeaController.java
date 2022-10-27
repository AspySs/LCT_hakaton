package com.hack.app.controller;


import com.hack.app.entity.Ideas;
import com.hack.app.exceptions.IdeaNotFoundException;
import com.hack.app.service.IdeasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/ideas")
public class IdeaController {
    IdeasService service;

    @Autowired
    public IdeaController(IdeasService service){this.service = service;}

    @GetMapping("/find/all")
    public ResponseEntity<List<Ideas>> findAll() {
        List<Ideas> ideasList = service.findAll();
        return new ResponseEntity<>(ideasList, HttpStatus.OK);
    }

    @GetMapping("/find/byTag/{tag}")
    public ResponseEntity<List<Ideas>> findByTag(@PathVariable("tag") String tag) {
        try {
            List<Ideas> ideasList = service.findByTagLike(tag);
            return new ResponseEntity<>(ideasList, HttpStatus.OK);
        }
        catch (IdeaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find/bySphere/{sphere}")
    public ResponseEntity<List<Ideas>> findBySphere(@PathVariable("sphere") String sphere) {
        try {
            List<Ideas> ideasList = service.findBySphere(sphere);
            return new ResponseEntity<>(ideasList, HttpStatus.OK);
        }
        catch (IdeaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find/byId/{id}")
    public ResponseEntity<Ideas> findByTag(@PathVariable("id") Integer id) {
        try {
            Ideas idea = service.findById(id);
            return new ResponseEntity<>(idea, HttpStatus.OK);
        }
        catch (IdeaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ideas> deleteById(@PathVariable("id") Integer id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IdeaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Ideas> add(@RequestBody Ideas idea) {
        try {
            service.add(idea);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
