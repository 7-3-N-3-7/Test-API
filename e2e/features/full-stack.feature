Feature: Full Stack Interaction
  Scenario: Verify public API data appears in the UI
    # This step interacts with the Java backend via HTTP
    Given the backend API is up and running
    # This step interacts with the React frontend via Playwright
    When I visit the public hello page in the UI
    # This step bridges both to ensure the UI correctly displays the API data
    Then I should see the message "Hello from public endpoint! No token needed." on the screen