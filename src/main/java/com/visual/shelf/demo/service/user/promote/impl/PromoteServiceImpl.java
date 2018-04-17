package com.visual.shelf.demo.service.user.promote.impl;

import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.promote.api.PromoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromoteServiceImpl implements PromoteService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<AuthorityLevel> promoteUser(String userName, AuthorityLevel authorityLevel) {
        Optional<User> user = userRepository.findByUserName(userName);
        if(!user.isPresent()){
            return Optional.empty();
        }
        user.get().setAuthorityLevel(authorityLevel);
        userRepository.save(user.get());
        return Optional.of(authorityLevel);
    }
}
