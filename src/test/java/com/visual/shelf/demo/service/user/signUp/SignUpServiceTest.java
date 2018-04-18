package com.visual.shelf.demo.service.user.signUp;

import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.signup.api.SignUpService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SignUpServiceTest {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void clearDataBefore() {
        userRepository.deleteAll();
    }

    @Test
    public void existingUserSignUpTest() {
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();

        userRepository.save(user);

        assertFalse(signUpService.signUp(user).isPresent());
    }

    @Test
    public void nonExistingUserSignUpTest() {
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();
        assertTrue(signUpService.signUp(user).isPresent());
        assertEquals(user, userRepository.findByUserName("tarrok").get());
    }


    @After
    public void clearDataAfter() {
        userRepository.deleteAll();
    }


}
