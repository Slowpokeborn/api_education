package com.example.helper.rest.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.example.helper.AllureAttachment.attachToAllureTextMessage;
import static io.restassured.http.ContentType.JSON;

public class Specification {


    // region RequestSpecification for "service"
    public static RequestSpecification getServiceRequestSpecification(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification getServiceRequestSpecification(String baseUrl, AuthenticationScheme authScheme) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAuth(authScheme)
                .setContentType(JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }
    // region RequestSpecification for "service"
}
