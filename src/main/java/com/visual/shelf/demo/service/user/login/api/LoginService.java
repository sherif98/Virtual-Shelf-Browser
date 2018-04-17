package com.visual.shelf.demo.service.user.login.api;

import com.visual.shelf.demo.db.entites.User;

import java.util.Optional;

public interface LoginService {

     Optional<User> login(String userName, String password);
}
