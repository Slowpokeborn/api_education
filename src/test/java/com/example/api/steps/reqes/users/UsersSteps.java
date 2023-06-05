package com.example.api.steps.reqes.users;

import com.example.services.reqres.entities.UserCreated;
import com.example.services.reqres.entities.UserToken;
import com.example.services.reqres.response.ErrorResponse;
import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Clock;

import static com.example.helper.AllureAttachment.attachToAllureJsonPathWithPrettify;
import static com.example.helper.date.DateTimeUtil.getStringFromDateTimeUTC;
import static com.example.helper.logger.MessageFormatter.format;
import static com.example.helper.mapping.JsonPathMapping.convertJsonPathToAnyObject;
import static com.example.helper.rest.HttpCode.*;
import static com.example.helper.rest.request.DeleteRequest.doHttpDeleteRequest;
import static com.example.helper.rest.request.GetRequest.doHttpGetRequest;
import static com.example.helper.rest.request.PatchRequest.doHttpPatchRequest;
import static com.example.helper.rest.request.PostRequest.doHttpPostRequest;
import static com.example.helper.rest.request.PutRequest.doHttpPutRequest;
import static com.example.helper.rest.spec.Specification.getServiceRequestSpecification;
import static com.example.services.reqres.EndPoints.*;

public class UsersSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersSteps.class);

    private final static String USERS_PARAMETERS = "{}/{}";


    @Step("Получить пользователя по id = \"{id}\"")
    public static UsersResponse getUserStep(int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath =  doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                OK
        );

        attachToAllureJsonPathWithPrettify(UsersResponse.class.getSimpleName(), jsonPath);

        UsersResponse usersResponse = convertJsonPathToAnyObject(jsonPath, UsersResponse.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), usersResponse.toString());

        return usersResponse;
    }

    @Step("Не получить пользователя по id = \"{id}\"")
    public static UsersResponse getNoUserStep(int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);

        LOGGER.info("GET REQUEST -> {}{}", BASE_URL.getValue(), path);

        JsonPath jsonPath = doHttpGetRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                NOT_FOUND
        );

        attachToAllureJsonPathWithPrettify(UsersResponse.class.getSimpleName(), jsonPath);

        UsersResponse usersResponse = convertJsonPathToAnyObject(jsonPath, UsersResponse.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), usersResponse.toString());

        return usersResponse;
    }

    @Step("Зарегестрировать пользователя ")
    public static UserToken regUserStep(String body) {
        String path = format(REGISTER.getValue());

        JsonPath jsonPath = doHttpPostRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                OK
        );

        attachToAllureJsonPathWithPrettify(UserToken.class.getSimpleName(), jsonPath);

        UserToken userToken = convertJsonPathToAnyObject(jsonPath, UserToken.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), userToken.toString());

        return userToken;
    }

    @Step("Неуспешно зарегестрировать пользователя ")
    public static ErrorResponse dontRegUserStep(String body) {
        String path = format(REGISTER.getValue());

        JsonPath jsonPath = doHttpPostRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                BAD_REQUEST
        );

        attachToAllureJsonPathWithPrettify(ErrorResponse.class.getSimpleName(), jsonPath);

        ErrorResponse errorResponse = convertJsonPathToAnyObject(jsonPath, ErrorResponse.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), errorResponse.toString());

        return errorResponse;
    }

    @Step("Логин пользователя ")
    public static UserToken logUserStep(String body) {
        String path = format(LOGIN.getValue());

        JsonPath jsonPath = doHttpPostRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                OK
        );
        attachToAllureJsonPathWithPrettify(UserToken.class.getSimpleName(), jsonPath);

        UserToken userToken = convertJsonPathToAnyObject(jsonPath, UserToken.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), userToken.toString());

        return userToken;
    }

    @Step("Неуспешный логин пользователя ")
    public static ErrorResponse dontLogUserStep(String body) {
        String path = format(LOGIN.getValue());

        JsonPath jsonPath = doHttpPostRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                BAD_REQUEST
        );

        attachToAllureJsonPathWithPrettify(ErrorResponse.class.getSimpleName(), jsonPath);

        ErrorResponse errorResponse = convertJsonPathToAnyObject(jsonPath, ErrorResponse.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), errorResponse.toString());

        return errorResponse;
    }

    @Step("Создание пользователя ")
    public static UserCreated createUserStep(String body) {
        String path = format(USERS.getValue());

        JsonPath jsonPath = doHttpPostRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                CREATED
        );
        attachToAllureJsonPathWithPrettify(UserCreated.class.getSimpleName(), jsonPath);

        UserCreated userCreated = convertJsonPathToAnyObject(jsonPath, UserCreated.class);
        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), userCreated.toString());

        return userCreated;
    }

    @Step("Апдейт пользователя")
    public static UserCreated putUserStep(String body, int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);
        String regex = "(.{5})$";


        JsonPath jsonPath = doHttpPutRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                OK
        );
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");

        attachToAllureJsonPathWithPrettify(UserCreated.class.getSimpleName(), jsonPath);

        UserCreated userCreated = convertJsonPathToAnyObject(jsonPath, UserCreated.class);
        userCreated.setCurrentTime(currentTime);

        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), userCreated.toString());

        return userCreated;
    }

    @Step("Апдейт пользователя")
    public static UserCreated patchUserStep(String body, int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);
        String regex = "(.{5})$";


        JsonPath jsonPath = doHttpPatchRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                body,
                OK
        );
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");

        attachToAllureJsonPathWithPrettify(UserCreated.class.getSimpleName(), jsonPath);

        UserCreated userCreated = convertJsonPathToAnyObject(jsonPath, UserCreated.class);
        userCreated.setCurrentTime(currentTime);

        LOGGER.debug("User from {}\n{}", getStringFromDateTimeUTC(), userCreated.toString());

        return userCreated;
    }

    @Step("Удаление пользователя")
    public static void deleteUserStep(int id) {
        String path = format(USERS_PARAMETERS, USERS.getValue(), id);


        doHttpDeleteRequest(
                getServiceRequestSpecification(BASE_URL.getValue()),
                path,
                DELETED
        );

    }
}
