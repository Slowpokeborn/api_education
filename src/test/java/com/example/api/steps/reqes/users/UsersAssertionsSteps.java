package com.example.api.steps.reqes.users;

import com.example.services.reqres.entities.User;
import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.example.api.steps.reqes.AssertionsUtils.assertSupport;

public class UsersAssertionsSteps {

    @Step("Проверка данных пользователя")
    public static void assertUsersResponseStep(UsersResponse usersResponse) {
        SoftAssertions softly = new SoftAssertions();

        User user = usersResponse.getData();

        softly.assertThat(user.getEmail())
                .as("User email contain '@'")
                .contains("@");
        softly.assertThat(user.getAvatar())
                .as("User avatar contain 'https://reqres.in/img/faces/'")
                .contains("https://reqres.in/img/faces/");

        assertSupport(softly, usersResponse);

        softly.assertAll();
    }
}
