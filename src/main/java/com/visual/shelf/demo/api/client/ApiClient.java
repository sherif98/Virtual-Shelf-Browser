package com.visual.shelf.demo.api.client;

import com.visual.shelf.demo.api.dto.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClient {

    public Example queryApi() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=java", Example.class);
    }
}
