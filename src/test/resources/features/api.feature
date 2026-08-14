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

  Scenario: Access secured endpoint with valid token
    Given I have a valid token
    When I request the secured endpoint with the token
    Then the response status code should be 200
    And the response body should contain "Hello from secured endpoint"

  Scenario: Access secured endpoint with invalid token
    Given I have an invalid token
    When I request the secured endpoint with the token
    Then the response status code should be 403
    And the response body should contain "Forbidden: Invalid token"

  Scenario: Access secured endpoint with expired token
    Given I have an expired token
    When I request the secured endpoint with the token
    Then the response status code should be 401
    And the response body should contain "Unauthorized: Token expired"

    Scenario: Access secured endpoint with missing token
    When I request the secured endpoint without a token
    Then the response status code should be 401

    Scenario: Access secured endpoint with malformed token
    Given I have a malformed token
    When I request the secured endpoint with the malformed token
    Then the response status code should be 400
    And the response body should contain "Bad Request: Malformed token"
