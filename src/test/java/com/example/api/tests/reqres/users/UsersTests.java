package com.example.api.tests.reqres.users;

import com.example.services.reqres.entities.UserCreated;
import com.example.services.reqres.entities.UserToken;
import com.example.services.reqres.response.ErrorResponse;
import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.filters.TagStore.NEGATIVE;
import static com.example.api.filters.TagStore.POSITIVE;
import static com.example.api.steps.reqes.users.UsersAssertionsSteps.*;
import static com.example.api.steps.reqes.users.UsersSteps.*;

@Epic("API. Методы работы с пользователями")
@Feature("Метод \"/api/users/{id}\" - получение пользователя по Id")
public class UsersTests {

    @Test
    @Tag(POSITIVE)
    @DisplayName("Получение существующего пользователя")
    @Description("Получение существующего пользователя")
    public void getUsersExistedTest() {
        int id = 2;

        UsersResponse usersResponse = getUserStep(id);
        assertUsersResponseStep(usersResponse);
    }

    @Test
    @Tag(NEGATIVE)
    @DisplayName("Получение несуществующего пользователя")
    @Description("Получение несуществующего пользователя")
    public void getUsersNonexistentTest() {
        int id = 23;

        UsersResponse usersResponse = getNoUserStep(id);
        assertNonexistentUserResponseStep(usersResponse);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация пользователя")
    public void postRegUserTest() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        UserToken userToken = regUserStep(body);
        assertUserRegStep(userToken);
    }

    @Test
    @Tag(NEGATIVE)
    @DisplayName("Регистрация пользователя без пароля")
    @Description("Регистрация пользователя без пароля")
    public void postRegUserNegativeTest() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\"}";
        ErrorResponse errorResponse = dontRegUserStep(body);
        assertNoUserRegStep(errorResponse);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Логин пользователя")
    @Description("Логин пользователя")
    public void postLogUserTest() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        UserToken userToken = logUserStep(body);
        assertUserLogStep(userToken);
    }

    @Test
    @Tag(NEGATIVE)
    @DisplayName("Логин пользователя без пароля")
    @Description("Логин пользователя без пароля")
    public void postLogUserNegativeTest() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\"}";
        ErrorResponse errorResponse = dontLogUserStep(body);
        assertNoUserLogStep(errorResponse);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Создание пользователя")
    @Description("Создание пользователя")
    public void postCreateUserTest() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        UserCreated userCreated = createUserStep(body);
        assertUserCreatedStep(userCreated);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Изменение пользователя")
    @Description("Изменение пользователя")
    public void putCreateUserTest() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        int id = 2;

        UserCreated userCreated = putUserStep(body, id);
        assertUserPutStep(userCreated);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Изменение пользователя")
    @Description("Изменение пользователя")
    public void patchCreateUserTest() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        int id = 2;

        UserCreated userCreated = patchUserStep(body, id);
        assertUserPatchStep(userCreated);
    }

    @Test
    @Tag(POSITIVE)
    @DisplayName("Удаление пользователя")
    @Description("Удаление пользователя")
    public void deleteCreateUserTest() {
        int id = 2;

        deleteUserStep(id);
    }
}
