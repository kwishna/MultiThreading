package rest_apis.auths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.authentication.FormAuthConfig.springSecurity;

public class SpringSecurityTest {
    @Test
    public void testLogin() {
        RestAssured.baseURI = "http://localhost:8080";

        // Perform login
        Response response = given()
                .formParam("username", "user")
                .formParam("password", "password")
                .auth().form("user", "password", springSecurity())
                .when()
                .post("/login");

        // Check if login was successful
        response.then().statusCode(302); // Assuming a redirect on successful login

        // Use the session for subsequent requests
        String sessionId = response.getCookie("JSESSIONID");

        // Access a secured endpoint
        Response securedResponse = given()
                .cookie("JSESSIONID", sessionId)
                .when()
                .get("/secured/endpoint");

        // Validate the response
        securedResponse.then().statusCode(200);
    }

    @Test
    public void cookiesTest() {
        // Make the initial request and extract cookies
        Response response = given()
                .when()
                .get("/api/endpoint");

        Map<String, String> cookies = response.cookies();

        // Use the cookies in subsequent requests
        given()
                .cookies(cookies)
                .when()
                .get("/api/another-endpoint")
                .then()
                .statusCode(200);
    }

    @Test
    public void csrfTest() {
        // Step 1: Fetch the CSRF token
        Response response = given()
                .when()
                .get("http://example.com/api/get-csrf-token");

        // Assuming the CSRF token is returned in a JSON response
        String csrfToken = response.jsonPath().getString("csrfToken");

        // Alternatively, if the token is in a cookie
        // String csrfToken = response.getCookie("CSRFToken");

        // Step 2: Use the CSRF token in subsequent requests
        Response postResponse = given()
                .header("X-CSRF-Token", csrfToken) // Include the CSRF token in the header
                .contentType("application/json")
                .body("{\"key\":\"value\"}") // Example request body
                .when()
                .post("http://example.com/api/submit");

        // Step 3: Validate the response
        postResponse.then().statusCode(200); // Check for successful response
    }
}