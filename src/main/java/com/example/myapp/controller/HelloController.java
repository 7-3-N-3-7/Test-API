package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from public endpoint! No token needed.";
    }

    @GetMapping("/api/secured")
    public String securedEndpoint(Principal principal) {
        return "Hello, " + principal.getName() + "! This is a secured endpoint.";
    }
}
