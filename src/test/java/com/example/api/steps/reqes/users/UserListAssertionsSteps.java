package com.example.api.steps.reqes.users;

import com.example.services.reqres.response.UsersListResponse;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.example.api.steps.reqes.AssertionsUtils.assertSupport;

public class UserListAssertionsSteps {

    @Step("Проверка UsersListResponse со списком пользователей")
    public static void assertUsersListResponseStep(UsersListResponse usersListResponse) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(usersListResponse)
                .as("UsersList is not null")
                .isNotNull();
        softly.assertThat(usersListResponse.getData().size())
                .as("UsersList.data.size > 0")
                .isGreaterThan(0);

        assertSupport(softly, usersListResponse);

        softly.assertAll();
    }

    @Step("Проверка UsersList без списка пользователей")
    public static void assertEmptyUsersListResponseStep(UsersListResponse usersListResponse) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(usersListResponse)
                .as("UsersList is not null")
                .isNotNull();
        softly.assertThat(usersListResponse.getData().size())
                .as("UsersList.data.size = 0")
                .isEqualTo(0);

        assertSupport(softly, usersListResponse);

        softly.assertAll();
    }


}
