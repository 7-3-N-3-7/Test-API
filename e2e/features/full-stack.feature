Feature: Full Stack Interaction
  
  Scenario: User can successfully log in and access secured data
    Given the backend API is up and running
    When I visit the public hello page in the UI
    And I log in with username "testuser" and password "password"
    Then I should see the message "Welcome! You are logged in." on the screen
    When I click the "Fetch Secured Data from Spring Boot" button
    Then I should see the API response on the screen