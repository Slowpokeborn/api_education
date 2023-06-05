package com.example.helper.rest.request;

import com.example.helper.rest.HttpCode;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DeleteRequest {
    public static JsonPath doHttpDeleteRequest(
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
                .delete(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }
    public static JsonPath doHttpDeleteRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .when()
                .delete(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }
}
