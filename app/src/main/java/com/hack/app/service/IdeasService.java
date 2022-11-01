package com.hack.app.service;

import com.hack.app.entity.Ideas;
import com.hack.app.exceptions.IdeaNotFoundException;
import com.hack.app.repository.IdeasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IdeasService {
    @Autowired
    IdeasRepository repository;

    @Autowired
    public IdeasService(IdeasRepository repository){this.repository = repository;}

    public List<Ideas> findAll(){return repository.findAll();}
    public List<Ideas> findBySphere(String sphere){
        List<Ideas> ideas = repository.findBySphere(sphere);
        if(!ideas.isEmpty()){
            return ideas;
        }
        throw new IdeaNotFoundException("Ideas with this sphere not exists");
    }
    public List<Ideas> findByTagLike(String tag){
        List<Ideas> ideas = repository.findByTagContains(tag);
        if(!ideas.isEmpty()){
            return ideas;
        }
        throw new IdeaNotFoundException("Ideas with this tag not exists");
    }
    public Ideas findById(Integer id){
        Optional<Ideas> idea = repository.findById(id);
        if(idea.isPresent()){
            return idea.get();
        }
        throw new IdeaNotFoundException("Ideas with this id not exists");
    }
    @Transactional
    @Modifying
    public void deleteById(Integer id){
        Optional<Ideas> idea = repository.findById(id);
        if(idea.isPresent()){repository.deleteById(id);}
        throw new IdeaNotFoundException("Ideas with this id not exists");
    }

    @Transactional
    @Modifying
    public Ideas add(Ideas idea){
        if(!idea.getName().isEmpty() && !idea.getStatus().isEmpty() && !idea.getProject().isEmpty() && !idea.getTask().isEmpty()){
            return repository.save(idea);
        }
        throw new IllegalArgumentException("One of labels are empty!");
    }

}
