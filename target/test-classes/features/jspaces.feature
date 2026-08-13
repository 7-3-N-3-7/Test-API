Feature: jSpaces Caching Behavior
  As a developer
  I want to ensure data written to the GigaSpace cache can be retrieved
  So that application performance is improved

  Scenario: Write and read from jSpaces
    Given a new item with id "test-1" and value "cached-data"
    When I write the item to the cache
    Then I should be able to read the item with id "test-1" and get "cached-data"

  Scenario: Update an existing item in jSpaces
    Given a new item with id "test-2" and value "initial-data"
    And I write the item to the cache
    When I update the item with id "test-2" to value "updated-data"
    Then I should be able to read the item with id "test-2" and get "updated-data"

  Scenario: Read a non-existent item from jSpaces
    When I read the item with id "non-existent-id"
    Then I should get no result

  Scenario: Delete an item from jSpaces
    Given a new item with id "test-3" and value "data-to-delete"
    And I write the item to the cache
    When I delete the item with id "test-3"
    Then I should be able to read the item with id "test-3" and get no result

  Scenario: Fail to write an item with an empty id
    Given a new item with id "" and value "data"
    When I attempt to write the item to the cache
    Then an IllegalArgumentException should be thrown with message "ID cannot be null or empty"
