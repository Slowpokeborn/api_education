package com.example.api.tests.reqres.users;

import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.filters.TagStore.POSITIVE;
import static com.example.api.steps.reqes.users.UsersAssertionsSteps.assertUsersResponseStep;
import static com.example.api.steps.reqes.users.UsersSteps.getUserStep;

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
}
