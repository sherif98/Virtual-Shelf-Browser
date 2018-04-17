package com.visual.shelf.demo.service.user.signup.api;

import com.visual.shelf.demo.db.entites.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface SignUpService {


    Optional<User> signUp(User user);
}
