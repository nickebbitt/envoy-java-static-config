package com.nickebbitt.greeter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreeterController {

    private RestTemplate restTemplate;

    public GreeterController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/greet")
    public String greet() {
        final ResponseEntity<String> response = restTemplate.getForEntity("http://message-generator:8080/message", String.class);
        return "Hello " + response.getBody();
    }

}
