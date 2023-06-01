package com.example.api.steps.reqes.users;

import com.example.services.reqres.response.UsersListResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.helper.AllureAttachment.attachToAllureJsonPathWithPrettify;
import static com.example.helper.date.DateTimeUtil.getStringFromDateTimeUTC;
import static com.example.helper.logger.MessageFormatter.format;
import static com.example.helper.mapping.JsonPathMapping.convertJsonPathToAnyObject;
import static com.example.helper.rest.HttpCode.OK;
import static com.example.helper.rest.request.GetRequest.doHttpGetRequest;
import static com.example.helper.rest.spec.Specification.getServiceRequestSpecification;
import static com.example.services.reqres.EndPoints.BASE_URL;
import static com.example.services.reqres.EndPoints.USERS;

public class UsersListSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersListSteps.class);

    private final static String USERS_PARAMETERS = "{}?page={}";


    @Step("Получить список пользователей")
    public static UsersListResponse getUsersListStep(int page) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), page);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath = doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                OK
        );

        attachToAllureJsonPathWithPrettify(UsersListResponse.class.getSimpleName(), jsonPath);

        UsersListResponse usersListResponse = convertJsonPathToAnyObject(jsonPath, UsersListResponse.class);
        LOGGER.debug("UsersListResponse from {}\n{}", getStringFromDateTimeUTC(), usersListResponse.toString());

        return usersListResponse;
    }
}
