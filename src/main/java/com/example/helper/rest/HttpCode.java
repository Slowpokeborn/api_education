package com.example.helper.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.helper.logger.MessageFormatter.format;

@AllArgsConstructor
@Getter
public enum HttpCode {

    OK(
            "OK",
            200
    ),
    CREATED(
            "Created",
            201
    ),
    DELETED(
            "Deleted",
            204
    ),
    BAD_REQUEST(
            "Bad Request",
            400
    ),
    UNAUTHORIZED(
            "Unauthorized",
            401
    ),
    NOT_FOUND(
            "Not Found",
            404
    ),
    INTERNAL_SERVER_ERROR(
            "Internal Server Error",
            500
    );

    private final String description;
    private final int value;

    @Override
    public String toString() {
        return format("HttpCode:\n\t{description = \"{}\",\n\tvalue = \"{}\"\n}", description, value);
    }
}
