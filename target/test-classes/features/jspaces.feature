Feature: jSpaces Caching Behavior
  As a developer
  I want to ensure data written to the GigaSpace cache can be retrieved
  So that application performance is improved

  Scenario: Write and read from jSpaces
    Given a new item with id "test-1" and value "cached-data"
    When I write the item to the cache
    Then I should be able to read the item with id "test-1" and get "cached-data"
