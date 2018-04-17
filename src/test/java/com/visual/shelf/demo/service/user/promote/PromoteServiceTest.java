package com.visual.shelf.demo.service.user.promote;

import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.user.promote.api.PromoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoteServiceTest {

    @Autowired
    private PromoteService promoteService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void existingUserPromotion(){
        User user = User.builder().authorityLevel(AuthorityLevel.NORMAL_USER)
                .password("abcd")
                .userName("tarrok").build();

        userRepository.save(user);
        assertTrue(promoteService.promoteUser("tarrok", AuthorityLevel.ADMIN).isPresent());
        assertEquals(AuthorityLevel.ADMIN,userRepository.findByUserName("tarrok").get().getAuthorityLevel());
    }

    @Test
    public void nonExistingUserPromotion(){
        assertFalse(promoteService.promoteUser("tarrok", AuthorityLevel.ADMIN).isPresent());
    }


}
