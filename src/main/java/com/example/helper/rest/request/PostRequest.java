package com.example.helper.rest.request;

import com.example.helper.rest.HttpCode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostRequest {

    public static Response doHttpPostRequest(
            RequestSpecification requestSpec,
            String requestBody,
            HttpCode code
    ) {
        return given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(code.getValue())
                .extract().response();
    }

    public static JsonPath doHttpPostRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            String requestBody,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }

    public static JsonPath doHttpPostRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            JsonPath requestJsonBody,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .body(requestJsonBody.prettify())
                .when()
                .post(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }
}
