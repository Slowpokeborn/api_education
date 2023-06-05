package com.example.api.steps.reqes.resource;

import com.example.services.reqres.response.ResourceListResponse;
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
import static com.example.services.reqres.EndPoints.*;

public class ResourceListSteps {
    private static final Logger LOGGER = LogManager.getLogger(ResourceListSteps.class);



    @Step("Получить список ресурса")
    public static ResourceListResponse getResourceListStep() {
        String path = format(UNKNOWN.getValue());

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath = doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                OK
        );

        attachToAllureJsonPathWithPrettify(UsersListResponse.class.getSimpleName(), jsonPath);

        ResourceListResponse resourceListResponse = convertJsonPathToAnyObject(jsonPath, ResourceListResponse.class);
        LOGGER.debug("UsersListResponse from {}\n{}", getStringFromDateTimeUTC(), resourceListResponse.toString());

        return resourceListResponse;
    }
}
