Feature: API Access
  As a user
  I want to access public endpoints freely
  But I should be blocked from secured endpoints without a token

  Scenario: Access public endpoint
    When I request the public hello endpoint
    Then the response status code should be 200
    And the response body should contain "Hello from public endpoint"

  Scenario: Access secured endpoint without token
    When I request the secured endpoint
    Then the response status code should be 401
