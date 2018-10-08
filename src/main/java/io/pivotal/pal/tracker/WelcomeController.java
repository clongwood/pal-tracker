package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {
    private final String message;
    public WelcomeController(@Value("${WELCOME_MESSAGE}") String value){
        this.message = value;
    }

    @GetMapping("/")
    public String sayHello() {
        return  message;
    }

    @GetMapping("/name")
    public String sayHlloName() {
        return "helloName";
    }
}