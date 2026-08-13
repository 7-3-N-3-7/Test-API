package com.example.myapp.cucumber;

import com.example.myapp.MyAppApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(classes = MyAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @When("I request the public hello endpoint")
    public void iRequestThePublicHelloEndpoint() {
        response = restTemplate.getForEntity("/public/hello", String.class);
    }

    @When("I request the secured endpoint")
    public void iRequestTheSecuredEndpoint() {
        response = restTemplate.getForEntity("/api/secured", String.class);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @And("the response body should contain {string}")
    public void theResponseBodyShouldContain(String text) {
        assertTrue(response.getBody().contains(text));
    }
}
