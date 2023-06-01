package com.example.services.reqres;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndPoints {

    BASE_URL(
            "Base API URL reqres.in",
            "https://reqres.in/api/"
    ),

    USERS(
            "EndPoint - \"users\"",
            "users"
    );

    private final String description;
    private final String value;
}
