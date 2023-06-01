package com.example.api.tests.reqres.users;

import com.example.services.reqres.response.UsersListResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.filters.TagStore.NEGATIVE;
import static com.example.api.filters.TagStore.POSITIVE;
import static com.example.api.steps.reqes.users.UserListAssertionsSteps.assertEmptyUsersListResponseStep;
import static com.example.api.steps.reqes.users.UserListAssertionsSteps.assertUsersListResponseStep;
import static com.example.api.steps.reqes.users.UsersListSteps.getUsersListStep;

@Epic("API. Методы работы с пользователями")
@Feature("Метод \"/api/users?page=n\" - получение списка пользователей постранично")
public class UsersListTests {

    @Test
    @Tag(POSITIVE)
    @DisplayName("Получение всех существующих страниц пользователей")
    @Description("Получение списка пользователей постранично. Существующие страницы")
    public void getUsersListPerPageTest() {

        UsersListResponse usersListResponseFirst = getUsersListStep(1);
        assertUsersListResponseStep(usersListResponseFirst);

        UsersListResponse usersListResponseSecond = getUsersListStep(2);
        assertUsersListResponseStep(usersListResponseSecond);
    }

    @Test
    @Tag(NEGATIVE)
    @DisplayName("Reqres получение не существующей страницы пользователей")
    @Description("Reqres получение списка пользователей постранично. Не существующая страница")
    public void getNotExistedUsersListPageTest() {

        UsersListResponse usersListResponse = getUsersListStep(3);
        assertEmptyUsersListResponseStep(usersListResponse);
    }
}
