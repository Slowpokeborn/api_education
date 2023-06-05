package com.example.api.steps.reqes.resource;

import com.example.services.reqres.response.ResourceListResponse;
import com.example.services.reqres.response.UsersListResponse;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.example.api.steps.reqes.AssertionsUtils.assertSupport;

public class ResourceListAssertionSteps {
    @Step("Проверка ResourceListResponse со списком пользователей")
    public static void assertResourceListResponseStep(ResourceListResponse resourceListResponse) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(resourceListResponse)
                .as("ResourceList is not null")
                .isNotNull();
        softly.assertThat(resourceListResponse.getData().size())
                .as("ResourceList.data.size > 0")
                .isGreaterThan(0);

        assertSupport(softly, resourceListResponse);

        softly.assertAll();
    }
}
