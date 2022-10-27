package com.hack.app.controller;

import com.hack.app.entity.Reactions;
import com.hack.app.service.ReactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reactions")
public class ReactionsController {

    @Autowired
    ReactionsService service;

    @Autowired
    public ReactionsController(ReactionsService service){this.service = service;}

    @GetMapping("/count/idea/true/{id}")
    public ResponseEntity<Long> count_idea_1(@PathVariable("id") Integer id){
        Long count = service.countByReactionTrueAndIdea_Id(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/count/idea/false/{id}")
    public ResponseEntity<Long> count_idea_0(@PathVariable("id") Integer id){
        Long count = service.countByReactionFalseAndIdea_Id(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/count/comment/true/{id}")
    public ResponseEntity<Long> count_comment_1(@PathVariable("id") Integer id){
        Long count = service.countByReactionTrueAndComment_Id(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/count/comment/false/{id}")
    public ResponseEntity<Long> count_comment_0(@PathVariable("id") Integer id){
        Long count = service.countByReactionFalseAndComment_Id(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping(value = "/add_idea", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Reactions> add_idea(@RequestBody Reactions reaction) {
            service.add_to_idea(reaction);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add_comment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Reactions> add_comm(@RequestBody Reactions reaction) {
        service.add_to_comment(reaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
