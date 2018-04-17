package com.visual.shelf.demo.dto;

import com.visual.shelf.demo.db.entites.AuthorityLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoteUserRequest {

    private String userName;
    private AuthorityLevel authorityLevel;
}
