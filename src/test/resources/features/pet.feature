Feature: Petstore API - Pet lifecycle CRUD operations

  As a QA engineer
  I want to test the full lifecycle of a pet resource
  So that I can verify create, read, update, and delete work end-to-end

  Background:
    Given the Petstore API is available

  Scenario Outline: Create a new pet
    When I create a new pet with ID <petId> name "<petName>" and status "<status>"
    Then the response status code should be <expectedStatusCode>
    And the created pet name should be "<petName>"

    Examples:
      | petId | petName | status    | expectedStatusCode |
      | 77777 | Utku    | available | 200                |
      | 88888 | Buddy   | pending   | 200                |
      | 99999 | Luna    | sold      | 200                |

  Scenario Outline: Retrieve a pet by ID
    When I send a GET request for pet with ID <petId>
    Then the response status code should be <expectedStatusCode>
    And the retrieved pet name should be "<expectedName>"

    Examples:
      | petId | expectedName | expectedStatusCode |
      | 77777 | Utku         | 200                |
      | 88888 | Buddy        | 200                |

  Scenario Outline: Update a pet's status
    When I update pet ID <petId> status to "<newStatus>"
    Then the response status code should be <expectedStatusCode>
    And I send a GET request for pet with ID <petId>
    And the updated pet status should be "<newStatus>"

    Examples:
      | petId | newStatus | expectedStatusCode |
      | 77777 | sold      | 200                |
      | 88888 | available | 200                |

  Scenario Outline: Delete a pet and verify it is gone
    When I delete pet with ID <petId>
    Then the response status code should be <deleteStatusCode>
    And I send a GET request for pet with ID <petId>
    And the final response status code should be <getStatusCode>

    Examples:
      | petId | deleteStatusCode | getStatusCode |
      | 77777 | 200              | 404           |
      | 88888 | 200              | 404           |