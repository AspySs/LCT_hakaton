package com.hack.app.controller;


import com.hack.app.entity.Comments;
import com.hack.app.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService service;

    @Autowired
    public CommentsController(CommentsService service){this.service = service;}

    @GetMapping("/find/all")
    public ResponseEntity<List<Comments>> findAll(){
        List<Comments> comms = service.findAll();
        return new ResponseEntity<>(comms, HttpStatus.OK);
    }

    @GetMapping("/find/idea/{id}")
    public ResponseEntity<List<Comments>> findByIdea_id(@PathVariable("id") Integer id){
        List<Comments> comms = service.findByIdea_id(id);
        return new ResponseEntity<>(comms, HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Long> count(@PathVariable("id") Integer id){
        Long count = service.countByIdea_id(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Comments> delete(@PathVariable("id") Integer id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
