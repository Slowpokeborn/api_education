package com.example.helper.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ResponseHelper {

    /**
     * Получить список headers в отформатированном виде (prettify)
     *
     * @param headers - хидеры
     * @return - formatted String
     */
    public static String getFormattedHeaderList(Headers headers) {
        StringBuilder builder = new StringBuilder();

        for (Header header : headers) {
            builder.append("\t");
            builder.append(header.getName());
            builder.append(" : ");
            builder.append(header.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Получить Response Body в отформатированном виде (prettify) в зависимости от типа json, xml, html
     * иначе как простой текст
     *
     * @param response - Response
     * @return - formatted String
     */
    public static String getFormattedResponseBody(Response response) {

        String contentType = response.header("Content-Type");

        if (contentType != null) {
            if (contentType.contains("json")) {
                return response.getBody().jsonPath().prettify();
            }
            if (contentType.contains("xml")) {
                return response.getBody().xmlPath().prettify();
            }
            if (contentType.contains("html")) {
                return response.getBody().htmlPath().prettify();
            }
        }

        return response.getBody().asString();
    }
}
