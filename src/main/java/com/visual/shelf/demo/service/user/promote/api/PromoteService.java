package com.visual.shelf.demo.service.user.promote.api;

import com.visual.shelf.demo.db.entites.AuthorityLevel;

import java.util.Optional;

public interface PromoteService {

    Optional<AuthorityLevel> promoteUser(String userName, AuthorityLevel authorityLevel);
}
