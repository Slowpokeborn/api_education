package com.example.helper.rest.request;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.example.helper.rest.ResponseHelper.getFormattedHeaderList;
import static com.example.helper.rest.ResponseHelper.getFormattedResponseBody;

public class RestAssuredRequestFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(RestAssuredRequestFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Response response = filterContext.next(requestSpec, responseSpec);

        LOGGER.debug("<-- REQUEST -->\nHTTP_CLIENT => {}\nMETHOD => {}\nURL => {}\nHEADERS =>\n{}\nRequest Body =>\n{}\n",
                requestSpec.getHttpClient(),
                requestSpec.getMethod(),
                requestSpec.getURI(),
                getFormattedHeaderList(requestSpec.getHeaders()),
                requestSpec.getBody()
        );
        LOGGER.debug("<-- RESPONSE -->\nTIME => {} MILLISECONDS\nStatusCode => {}\nStatusLine => {}\nHEADERS =>\n{}\nResponse Body =>\n{}",
                response.getTimeIn(TimeUnit.MILLISECONDS),
                response.getStatusCode(),
                response.getStatusLine(),
                getFormattedHeaderList(response.getHeaders()),
                getFormattedResponseBody(response)
        );
        return response;
    }
}
