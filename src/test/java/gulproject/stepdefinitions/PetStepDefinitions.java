package gulproject.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class PetStepDefinitions {

    private static long createdPetId;
    private static String createdPetName;

    private Response response;

    @Given("the Petstore API is available")
    public void thePetstoreApiIsAvailable() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // ============ CREATE (POST) ============

    @When("I create a new pet with ID {long} name {string} and status {string}")
    public void iCreateANewPetWithIdNameAndStatus(long petId, String name, String status) {

        String requestBody = "{"
                + "\"id\": " + petId + ","
                + "\"name\": \"" + name + "\","
                + "\"status\": \"" + status + "\""
                + "}";

        response = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .log().all()
                .extract().response();

        createdPetId = response.jsonPath().getLong("id");
        createdPetName = response.jsonPath().getString("name");
    }

    @And("the created pet name should be {string}")
    public void theCreatedPetNameShouldBe(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName);
    }

    // ============ READ (GET) ============

    @When("I send a GET request for the created pet")
    public void iSendAGetRequestForTheCreatedPet() {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/" + createdPetId)
                .then()
                .log().all()
                .extract().response();
    }

    @And("the retrieved pet name should be {string}")
    public void theRetrievedPetNameShouldBe(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName);
    }

    // ============ UPDATE (PUT) ============

    @When("I update the pet's status to {string}")
    public void iUpdateThePetStatusTo(String newStatus) {

        String requestBody = "{"
                + "\"id\": " + createdPetId + ","
                + "\"name\": \"" + createdPetName + "\","
                + "\"status\": \"" + newStatus + "\""
                + "}";

        response = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .log().all()
                .extract().response();
    }

    @And("I send a GET request for the updated pet")
    public void iSendAGetRequestForTheUpdatedPet() {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/" + createdPetId)
                .then()
                .log().all()
                .extract().response();
    }

    @And("the updated pet status should be {string}")
    public void theUpdatedPetStatusShouldBe(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("status");
        Assert.assertEquals(actualStatus, expectedStatus);
    }

    // ============ DELETE ============

    @When("I delete the created pet")
    public void iDeleteTheCreatedPet() {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .delete("/pet/" + createdPetId)
                .then()
                .log().all()
                .extract().response();
    }

    @And("I send a GET request for the deleted pet")
    public void iSendAGetRequestForTheDeletedPet() {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/" + createdPetId)
                .then()
                .log().all()
                .extract().response();
    }

    // ============ SHARED ASSERTIONS ============

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @And("the final response status code should be {int}")
    public void theFinalResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }
}