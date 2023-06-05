package com.example.api.steps.reqes.users;

import com.example.services.reqres.entities.UserCreated;
import com.example.services.reqres.response.ErrorResponse;
import com.example.services.reqres.entities.User;
import com.example.services.reqres.entities.UserToken;
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

    @Step("Проверка несуществующего пользователя")
    public static void assertNonexistentUserResponseStep(UsersResponse usersResponse) {
        SoftAssertions softly = new SoftAssertions();

        User user = usersResponse.getData();

        softly.assertThat(user == null)
                .as("User is null");

    }

    @Step("Проверка регистрации пользователя")
    public static void assertUserRegStep(UserToken response) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.getId())
                .as("User id is: 4'")
                .isEqualTo(4);
        softly.assertThat(response.getToken())
                .as("User token is: 'QpwL5tke4Pnpja7X4'")
                .isEqualTo("QpwL5tke4Pnpja7X4");

        softly.assertAll();
    }

    @Step("Проверка неуспешной регистрации пользователя")
    public static void assertNoUserRegStep(ErrorResponse errorResponse) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(errorResponse.getError())
                .as("Error is 'Missing password'")
                .isEqualTo("Missing password");

        softly.assertAll();
    }

    @Step("Проверка логина пользователя")
    public static void assertUserLogStep(UserToken response) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.getToken())
                .as("User token is: 'QpwL5tke4Pnpja7X4'")
                .isEqualTo("QpwL5tke4Pnpja7X4");

        softly.assertAll();
    }

    @Step("Проверка неуспешного логина пользователя")
    public static void assertNoUserLogStep(ErrorResponse errorResponse) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(errorResponse.getError())
                .as("Error is 'Missing password'")
                .isEqualTo("Missing password");

        softly.assertAll();
    }

    @Step("Проверка создания пользователя")
    public static void assertUserCreatedStep(UserCreated response) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.getId())
                .as("User id is not null")
                .isNotEmpty();
        softly.assertThat(response.getName())
                .as("User name is 'morpheus'")
                .isEqualTo("morpheus");

        softly.assertAll();
    }


    @Step("Проверка изменения пользователя")
    public static void assertUserPutStep(UserCreated response) {
        SoftAssertions softly = new SoftAssertions();

        String regex = "(.{5})$";

        softly.assertThat(response.getUpdatedAt().replaceAll(regex,""))
                .as("User update time is current")
                .isEqualTo(response.getCurrentTime());

        softly.assertAll();
    }

    @Step("Проверка изменения пользователя")
    public static void assertUserPatchStep(UserCreated response) {
        SoftAssertions softly = new SoftAssertions();

        String regex = "(.{5})$";

        softly.assertThat(response.getUpdatedAt().replaceAll(regex,""))
                .as("User update time is current")
                .isEqualTo(response.getCurrentTime());

        softly.assertAll();
    }

}
