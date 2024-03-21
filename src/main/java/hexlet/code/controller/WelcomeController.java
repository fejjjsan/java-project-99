package hexlet.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public final class WelcomeController {
    @GetMapping("")
    public String home() {
        return "Home page!";
    }
    @GetMapping("welcome")
    public String index() {
        return "Welcome to Spring!";
    }
}
