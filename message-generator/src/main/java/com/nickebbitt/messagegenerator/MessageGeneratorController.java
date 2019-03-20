package com.nickebbitt.messagegenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageGeneratorController {

    @GetMapping
    public String message() {
        return "message";
    }
}
