package com.visual.shelf.demo.controller;


import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.dto.PromoteUserRequest;
import com.visual.shelf.demo.exception.DuplicateUserException;
import com.visual.shelf.demo.exception.UserNotFoundException;
import com.visual.shelf.demo.service.user.login.api.LoginService;
import com.visual.shelf.demo.service.user.promote.api.PromoteService;
import com.visual.shelf.demo.service.user.signup.api.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private PromoteService promoteService;

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public User login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return loginService.login(userName, password).orElseThrow(() -> new UserNotFoundException(userName));
    }

    @PutMapping
    public AuthorityLevel promoteUser(@RequestBody PromoteUserRequest promoteUserRequest) {
        return promoteService.promoteUser(promoteUserRequest.getUserName(), promoteUserRequest.getAuthorityLevel())
                .orElseThrow(() -> new UserNotFoundException(promoteUserRequest.getUserName()));
    }

    @PostMapping
    public User signUp(@RequestBody User user) {
        return signUpService.signUp(user).orElseThrow(() -> new DuplicateUserException(user.getUserName()));
    }

}