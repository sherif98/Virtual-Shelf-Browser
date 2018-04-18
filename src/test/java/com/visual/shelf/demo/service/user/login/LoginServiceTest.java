package com.visual.shelf.demo.service.user.login;


import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.login.api.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void clearDataBefore() {
        userRepository.deleteAll();
    }

    @Test
    public void existingUserTest() {
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();
        userRepository.save(user);

        Optional<User> result = loginService.login("tarrok", "abcd");
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void nonExisitingUserTest() {
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();
        userRepository.save(user);

        Optional<User> result = loginService.login("tarrok123", "abcd");
        assertFalse(result.isPresent());
    }

    @After
    public void clearDataAfter() {
        userRepository.deleteAll();
    }
}
