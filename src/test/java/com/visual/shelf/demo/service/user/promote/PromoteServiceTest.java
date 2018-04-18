package com.visual.shelf.demo.service.user.promote;

import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.promote.api.PromoteService;
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
public class PromoteServiceTest {

    @Autowired
    private PromoteService promoteService;
    @Autowired
    private UserRepository userRepository;


    @Before
    public void clearDataBefore() {
        userRepository.deleteAll();
        User admin = User.builder()
                .userName("admin")
                .password("admin")
                .authorityLevel(AuthorityLevel.ADMIN)
                .build();

        userRepository.save(admin);
    }

    @Test
    public void existingUserPromotion() {
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();

        final Optional<User> admin = userRepository.findByUserName("admin");
        assertTrue(admin.isPresent());

        userRepository.save(user);
        assertTrue(promoteService.promoteUser(admin.get().getId(),
                "tarrok", AuthorityLevel.ADMIN).isPresent());
        assertEquals(AuthorityLevel.ADMIN, userRepository.findByUserName("tarrok").get().getAuthorityLevel());
    }

    @Test
    public void nonExistingUserPromotion() {
        final Optional<User> admin = userRepository.findByUserName("admin");
        assertTrue(admin.isPresent());
        assertFalse(promoteService.promoteUser(admin.get().getId(),
                "tarrok", AuthorityLevel.ADMIN).isPresent());
    }

    @After
    public void clearDataAfter() {
        userRepository.deleteAll();
    }

}
