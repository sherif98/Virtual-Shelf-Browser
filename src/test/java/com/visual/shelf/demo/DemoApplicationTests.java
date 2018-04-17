package com.visual.shelf.demo;

import com.visual.shelf.demo.api.client.ApiClient;
import com.visual.shelf.demo.api.dto.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    private ApiClient apiClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testApiCall() {
        final Example res = apiClient.queryApi();
        System.out.println(res);
        assertTrue(true);
    }

}
