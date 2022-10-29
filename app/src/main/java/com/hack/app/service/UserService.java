package com.hack.app.service;


import com.hack.app.entity.User;
import com.hack.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){this.repository = repository;}

    public void update(@Nullable String name, @Nullable String info, @Nullable String status, String login){
        if(repository.existsByLogin(login)){
            repository.updateNameAndInfoAndStatusByLogin(name, info, status, login);
        }else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    public User findUserByLogin(String login){
        Optional<User> user = repository.findUserByLogin(login);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("user not found");
    }
}
