package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String greeting;
    public WelcomeController(@Value("${WELCOME_MESSAGE}") String greeting) {
        this.greeting=greeting;
    }
    @GetMapping("/hello")
    public String sayHello() {
        return greeting;
    }
    @GetMapping("/")
    public String sayHi() {
        return sayHello();
    }
    @GetMapping("/goodbye")
    public String sayGoodBye() {
        return "bye";
    }
}