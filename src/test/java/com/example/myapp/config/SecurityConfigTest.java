package com.example.myapp.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicEndpointIsAccessible() throws Exception {
        mockMvc.perform(get("/public/hello"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSecuredEndpointReturns401WithoutToken() throws Exception {
        mockMvc.perform(get("/api/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testCorsPreflightIsAllowed() throws Exception {
        mockMvc.perform(options("/api/secured")
                        .header("Origin", "http://localhost:5173")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk());
    }
}
