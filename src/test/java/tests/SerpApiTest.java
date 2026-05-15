package tests;

import base.BaseTest;
import constants.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class SerpApiTest extends BaseTest {

    @Test
    public void verifyCoffeeSearch() {

        Response response =

                given()

                        .queryParam("q", "Coffee")

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
                        
                        .queryParam(
                                "api_key",
                                ConfigReader.getProperty("api.key")
                        )

                .when()

                        .get(Endpoints.SEARCH)

                .then()

                        .extract()
                        .response();

        // Print response
        System.out.println(
                response.asPrettyString()
        );

        // Status validation
        Assert.assertEquals(
                response.statusCode(),
                200
        );

        // JSON validation
        String status =
                response.jsonPath()
                        .getString(
                                "search_metadata.status"
                        );

        Assert.assertEquals(
                status,
                "Success"
        );

        // Extract first title
        String firstTitle =
                response.jsonPath()
                        .getString(
                                "organic_results[0].title"
                        );

        System.out.println(
                "First Result Title: "
                        + firstTitle
        );
    }
}