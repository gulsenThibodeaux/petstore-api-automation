# Petstore API Automation Framework

An end-to-end API test automation framework built in Java that validates the full CRUD lifecycle of a REST API resource using BDD-style scenarios.

## Tech Stack

- **Java 21** — programming language
- **Maven** — dependency and build management
- **Rest Assured 5.4** — HTTP client for API calls
- **Cucumber 7.15** — BDD framework for feature files and step definitions
- **TestNG 7.9** — test execution and assertions
- **Jackson** — JSON serialization

## What This Project Tests

The framework tests the Swagger Petstore API (`https://petstore.swagger.io/v2`) across four scenarios covering the full CRUD lifecycle of a pet resource:

1. **Create** — POST a new pet with a specific ID, name, and status
2. **Read** — GET the created pet and verify data integrity
3. **Update** — PUT to change the pet's status, then GET to verify the update persisted
4. **Delete** — DELETE the pet, then GET to confirm a 404 response

Test data flows between scenarios using shared context — the pet ID created in scenario 1 is reused in all subsequent scenarios.

## Project Structure
