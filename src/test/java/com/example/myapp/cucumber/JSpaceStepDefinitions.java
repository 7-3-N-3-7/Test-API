package com.example.myapp.cucumber;

import com.example.myapp.service.CacheService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSpaceStepDefinitions {

    @Autowired
    private CacheService cacheService;

    private String testId;
    private String testValue;

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
}
