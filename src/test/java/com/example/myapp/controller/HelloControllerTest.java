package com.example.myapp.controller;

import org.junit.jupiter.api.Test;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloControllerTest {

    @Test
    public void testPublicHello() {
        HelloController controller = new HelloController();
        String result = controller.publicHello();
        assertEquals("Hello from public endpoint! No token needed.", result);
    }

    @Test
    public void testSecuredEndpoint() {
        HelloController controller = new HelloController();
        Principal mockPrincipal = () -> "testUser";
        
        String result = controller.securedEndpoint(mockPrincipal);
        assertEquals("Hello, testUser! This is a secured endpoint.", result);
    }
}
