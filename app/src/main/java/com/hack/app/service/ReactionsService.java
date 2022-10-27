package com.hack.app.service;

import com.hack.app.entity.Reactions;
import com.hack.app.exceptions.ReactionNotFoundException;
import com.hack.app.repository.ReactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionsService {

    @Autowired
    ReactionsRepository repository;

    @Autowired
    public ReactionsService(ReactionsRepository repository){this.repository = repository;}

    public long countByReactionTrueAndIdea_Id(Integer id){
        return repository.countByReactionTrueAndIdea_Id(id);
    }

    public long countByReactionFalseAndIdea_Id(Integer id){
        return repository.countByReactionFalseAndIdea_Id(id);
    }

    public long countByReactionTrueAndComment_Id(Integer id){
        return repository.countByReactionTrueAndComment_Id(id);
    }

    public long countByReactionFalseAndComment_Id(Integer id){
        return repository.countByReactionFalseAndComment_Id(id);
    }

    public boolean existsByUser_idAndIdea_Id(Integer user_id, Integer id){
        return repository.existsByUser_idAndIdea_Id(user_id, id);
    }

    public boolean existsByUser_idAndComment_Id(Integer user_id, Integer id){
        return repository.existsByUser_idAndComment_Id(user_id, id);
    }

    public Reactions findByUser_idAndIdea_Id(Integer user_id, Integer id){
        Optional<Reactions> reaction = repository.findByUser_idAndIdea_Id(user_id, id);
        if(reaction.isPresent()){
            return reaction.get();
        }
        throw new ReactionNotFoundException("reaction not exists");
    }

    public Reactions findByUser_idAndComment_Id(Integer user_id, Integer id){
        Optional<Reactions> reaction = repository.findByUser_idAndComment_Id(user_id, id);
        if(reaction.isPresent()){
            return reaction.get();
        }
        throw new ReactionNotFoundException("reaction not exists");
    }

    public Reactions add_to_idea(Reactions reaction){
        if(existsByUser_idAndIdea_Id(reaction.getUser_id(), reaction.getIdea().getId())){
            Optional<Reactions> react = repository.findByUser_idAndIdea_Id(reaction.getUser_id(), reaction.getIdea().getId());
            react.ifPresent(reactions -> repository.deleteById(reactions.getId()));
        }
        return repository.save(reaction);
    }

    public Reactions add_to_comment(Reactions reaction){
        if(existsByUser_idAndComment_Id(reaction.getUser_id(), reaction.getComment().getId())){
            Optional<Reactions> react = repository.findByUser_idAndComment_Id(reaction.getUser_id(), reaction.getComment().getId());
            react.ifPresent(reactions -> repository.deleteById(reactions.getId()));
        }
        return repository.save(reaction);
    }

}
