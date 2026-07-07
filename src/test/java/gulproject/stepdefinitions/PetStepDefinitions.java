package gulproject.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import gulproject.models.Pet;
import gulproject.utilities.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class PetStepDefinitions {

    private Response response;

    @Given("the Petstore API is available")
    public void thePetstoreApiIsAvailable() {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }

    // ============ CREATE (POST) ============

    @When("I create a new pet with ID {long} name {string} and status {string}")
    public void iCreateANewPetWithIdNameAndStatus(long petId, String name, String status) {
        Pet pet = new Pet(petId, name, status);

        response = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .log().all()
                .extract().response();
    }

    @And("the created pet name should be {string}")
    public void theCreatedPetNameShouldBe(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName);
    }

    // ============ READ (GET) ============

    @When("I send a GET request for pet with ID {long}")
    public void iSendAGetRequestForPetWithId(long petId) {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/" + petId)
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

    @When("I update pet ID {long} status to {string}")
    public void iUpdatePetIdStatusTo(long petId, String newStatus) {
        String currentName = RestAssured.get("/pet/" + petId).jsonPath().getString("name");

        Pet updatedPet = new Pet(petId, currentName, newStatus);

        response = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updatedPet)
                .when()
                .put("/pet")
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

    @When("I delete pet with ID {long}")
    public void iDeletePetWithId(long petId) {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .delete("/pet/" + petId)
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