package com.example.api.steps.reqes.resource;

import com.example.api.steps.reqes.users.UsersSteps;
import com.example.services.reqres.response.ResourceResponse;
import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.helper.AllureAttachment.attachToAllureJsonPathWithPrettify;
import static com.example.helper.date.DateTimeUtil.getStringFromDateTimeUTC;
import static com.example.helper.logger.MessageFormatter.format;
import static com.example.helper.mapping.JsonPathMapping.convertJsonPathToAnyObject;
import static com.example.helper.rest.HttpCode.NOT_FOUND;
import static com.example.helper.rest.HttpCode.OK;
import static com.example.helper.rest.request.GetRequest.doHttpGetRequest;
import static com.example.helper.rest.spec.Specification.getServiceRequestSpecification;
import static com.example.services.reqres.EndPoints.*;

public class ResourceSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersSteps.class);

    private final static String USERS_PARAMETERS = "{}/{}";


    @Step("Получить ресурс по id = \"{id}\"")
    public static ResourceResponse getResourceStep(int id) {
        String path = format(USERS_PARAMETERS, UNKNOWN.getValue(), id);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath =  doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                OK
        );

        attachToAllureJsonPathWithPrettify(ResourceResponse.class.getSimpleName(), jsonPath);

        ResourceResponse resourceResponse = convertJsonPathToAnyObject(jsonPath, ResourceResponse.class);
        LOGGER.debug("Resource from {}\n{}", getStringFromDateTimeUTC(), resourceResponse.toString());

        return resourceResponse;
    }

    @Step("Получить несуществующий ресурс по id = \"{id}\"")
    public static ResourceResponse getNoResourceStep(int id) {
        String path = format(USERS_PARAMETERS, UNKNOWN.getValue(), id);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath = doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                NOT_FOUND
        );

        attachToAllureJsonPathWithPrettify(UsersResponse.class.getSimpleName(), jsonPath);

        ResourceResponse resourceResponse = convertJsonPathToAnyObject(jsonPath, ResourceResponse.class);
        LOGGER.debug("Resource from {}\n{}", getStringFromDateTimeUTC(), resourceResponse.toString());

        return resourceResponse;
    }
}
