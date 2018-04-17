package com.visual.shelf.demo.service.user.signup.impl;

import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.signup.api.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> signUp(User user) {

        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }
}
