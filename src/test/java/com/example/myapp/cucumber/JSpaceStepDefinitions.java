package com.example.myapp.cucumber;

import com.example.myapp.service.CacheService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JSpaceStepDefinitions {

    @Autowired
    private CacheService cacheService;

    private String testId;
    private String testValue;
    private Exception exception;

    @Given("a new item with id {string} and value {string}")
    public void aNewItemWithIdAndValue(String id, String value) {
        this.testId = id;
        this.testValue = value;
    }

    @When("I write the item to the cache")
    public void iWriteTheItemToTheCache() {
        cacheService.writeToCache(testId, testValue);
    }

    @Then("I should be able to read the item with id {string} and get {string}")
    public void iShouldBeAbleToReadTheItemWithIdAndGet(String id, String expectedValue) {
        String actualValue = cacheService.readFromCache(id);
        assertEquals(expectedValue, actualValue);
    }

    @When("I update the item with id {string} to value {string}")
    public void iUpdateTheItemWithIdToValue(String id, String value) {
        cacheService.writeToCache(id, value);
    }

    @When("I read the item with id {string}")
    public void iReadTheItemWithId(String id) {
        this.testValue = cacheService.readFromCache(id);
    }

    @Then("I should get no result")
    public void iShouldGetNoResult() {
        assertNull(this.testValue);
    }

    @When("I delete the item with id {string}")
    public void iDeleteTheItemWithId(String id) {
        cacheService.deleteFromCache(id);
    }

    @Then("I should be able to read the item with id {string} and get no result")
    public void iShouldBeAbleToReadTheItemWithIdAndGetNoResult(String id) {
        String actualValue = cacheService.readFromCache(id);
        assertNull(actualValue);
    }

    @When("I attempt to write the item to the cache")
    public void iAttemptToWriteTheItemToTheCache() {
        try {
            cacheService.writeToCache(testId, testValue);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Then("an IllegalArgumentException should be thrown with message {string}")
    public void anIllegalArgumentExceptionShouldBeThrownWithMessage(String expectedMessage) {
        assertNotNull(exception, "Expected an exception to be thrown");
        assertTrue(exception instanceof IllegalArgumentException, "Expected IllegalArgumentException but got " + exception.getClass().getName());
        assertEquals(expectedMessage, exception.getMessage());
    }
}
