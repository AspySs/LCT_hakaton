package com.hack.app.service;


import com.hack.app.entity.Comments;
import com.hack.app.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository repository;

    @Autowired
    public CommentsService(CommentsRepository repository){this.repository = repository;}

    public long countByIdea_id(Integer idea_id){
        return repository.countByIdea_id(idea_id);
    }

    public List<Comments> findByIdea_id(Integer idea_id){
        return repository.findByIdea_id(idea_id);
    }

    public void deleteById(Integer integer){
        repository.deleteById(integer);
    }

    public List<Comments> findAll(){
        return repository.findAll();
    }
}
