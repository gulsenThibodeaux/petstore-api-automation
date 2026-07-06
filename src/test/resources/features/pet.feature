Feature: Petstore API - Pet lifecycle CRUD operations

  As a QA engineer
  I want to test the full lifecycle of a pet resource
  So that I can verify create, read, update, and delete work end-to-end

  Scenario: Create a new pet
    Given the Petstore API is available
    When I create a new pet with ID 77777 name "Utku" and status "available"
    Then the response status code should be 200
    And the created pet name should be "Utku"

  Scenario: Retrieve the created pet
    Given the Petstore API is available
    When I send a GET request for the created pet
    Then the response status code should be 200
    And the retrieved pet name should be "Utku"

  Scenario: Update the pet's status
    Given the Petstore API is available
    When I update the pet's status to "sold"
    Then the response status code should be 200
    And I send a GET request for the updated pet
    And the updated pet status should be "sold"

  Scenario: Delete the pet and verify it is gone
    Given the Petstore API is available
    When I delete the created pet
    Then the response status code should be 200
    And I send a GET request for the deleted pet
    And the final response status code should be 404