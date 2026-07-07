Petstore API Automation Framework
An end-to-end API test automation framework built in Java that validates the full CRUD lifecycle of a REST API resource using BDD-style scenarios.
Tech Stack

Java 21
Maven
Rest Assured 5.4
Cucumber 7.15
TestNG 7.9
Jackson

What This Project Tests
The framework tests the Swagger Petstore API across four scenarios covering the full CRUD lifecycle of a pet resource:

Create — POST a new pet with a specific ID, name, and status
Read — GET the created pet and verify data integrity
Update — PUT to change the pet's status, then GET to verify the update persisted
Delete — DELETE the pet, then GET to confirm a 404 response

Test data flows between scenarios using shared context — the pet ID created in scenario 1 is reused in all subsequent scenarios.
Project Structure

src/test/java/gulproject/runners/TestRunner.java — Cucumber + TestNG entry point
src/test/java/gulproject/stepdefinitions/PetStepDefinitions.java — Gherkin to Java glue code
src/test/resources/features/pet.feature — BDD scenarios in Gherkin
pom.xml — Maven dependencies

How to Run
Prerequisites: Java 21+ and Maven installed.
Run: mvn clean test
Or run directly from IntelliJ by executing the TestRunner class.
Reports
After a test run, HTML and JSON reports are generated in the target/cucumber-reports/ folder. Open the HTML report in a browser for a visual test summary.
Key Design Decisions

Cucumber for BDD — feature files can be read by non-technical stakeholders
Full request/response logging — every API call logs headers, body, and status for easy debugging
Shared state between scenarios — the pet ID is stored after creation so subsequent scenarios can reuse it
Explicit assertions on response fields — tests verify actual response body content changed as expected

Author
Gulsen Thibodeaux — QA Automation Engineer / SDET
