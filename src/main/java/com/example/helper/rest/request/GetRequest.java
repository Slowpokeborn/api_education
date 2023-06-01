package com.example.helper.rest.request;

import com.example.helper.rest.HttpCode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest {

    public static JsonPath doHttpGetRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .when()
                .get(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }

    public static JsonPath doHttpGetRequest(
            RequestSpecification requestSpec,
            String requestMethod,
            Map<String, Object> queryParams,
            HttpCode code
    ) {
        return new JsonPath(given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get(requestMethod)
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString()
        );
    }

    public static String doHttpGetAuthRequest(
            RequestSpecification requestSpec,
            HttpCode code
    ) {
        return given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .when()
                .get()
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().asString();
    }

    public static Response doHttpGetAuthSoapRequest(
            RequestSpecification requestSpec,
            HttpCode code
    ) {
        return given()
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .when()
                .get()
                .then()
                //.log().all()
                .statusCode(code.getValue())
                .extract().response();
    }
}
