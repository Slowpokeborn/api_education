package com.example.helper.rest.request;

import com.example.helper.rest.HttpCode;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PatchRequest {
    public static JsonPath doHttpPatchRequest(
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
                .patch(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }
}
