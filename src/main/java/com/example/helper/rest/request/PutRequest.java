package com.example.helper.rest.request;

import com.example.helper.rest.HttpCode;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PutRequest {
    public static JsonPath doHttpPutRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            String requestJsonBody,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .body(requestJsonBody)
                .when()
                .put(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }

}
