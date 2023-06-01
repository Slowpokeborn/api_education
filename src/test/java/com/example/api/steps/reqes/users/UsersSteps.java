package com.example.api.steps.reqes.users;

import com.example.services.reqres.response.UsersResponse;
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

public class UsersSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersSteps.class);

    private final static String USERS_PARAMETERS = "{}/{}";


    @Step("Получить пользователя по id = \"{id}\"")
    public static UsersResponse getUserStep(int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath = doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                OK
        );

        attachToAllureJsonPathWithPrettify(UsersResponse.class.getSimpleName(), jsonPath);

        UsersResponse usersResponse = convertJsonPathToAnyObject(jsonPath, UsersResponse.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), usersResponse.toString());

        return usersResponse;
    }
}
