package base;

import io.restassured.RestAssured;

public class BaseTest {

    public static void setup() {

        RestAssured.baseURI =
                "https://serpapi.com";
    }
}