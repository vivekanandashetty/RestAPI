package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class SerpApiSteps {

    Response response;

    @Given("user sets SerpApi base URL")
    public void setupBaseUrl() {

        BaseTest.setup();
    }

    @When("user searches for {string}")
    public void searchCoffee(String searchText) {

        response =

                given()

                        .queryParam("q", searchText)

                        .queryParam(
                                "location",
                                "Austin, Texas, United States"
                        )

                        .queryParam("hl", "en")

                        .queryParam("gl", "us")

                        .queryParam(
                                "google_domain",
                                "google.com"
                        )

                .when()

                        .get("/search.json")

                .then()

                        .extract()
                        .response();

        System.out.println(
                response.asPrettyString()
        );
    }

    @Then("response status code should be 200")
    public void verifyStatusCode() {

        Assert.assertEquals(
                response.statusCode(),
                200
        );
    }
}