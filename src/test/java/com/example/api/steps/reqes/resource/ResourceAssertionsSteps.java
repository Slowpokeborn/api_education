package com.example.api.steps.reqes.resource;

import com.example.services.reqres.entities.Color;
import com.example.services.reqres.response.ResourceResponse;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.example.api.steps.reqes.AssertionsUtils.assertSupport;

public class ResourceAssertionsSteps {
    @Step("Проверка данных ресурса")
    public static void assertResourceResponseStep(ResourceResponse resourceResponse) {
        SoftAssertions softly = new SoftAssertions();

        Color color = resourceResponse.getData();

        softly.assertThat(color.getColor())
                .as("Resource color contain '#'")
                .contains("#");
        softly.assertThat(color.getPantone_value())
                .as("Resource color contain '-")
                .contains("-");

        assertSupport(softly, resourceResponse);

        softly.assertAll();
    }
    @Step("Проверка несуществующего ресурса")
    public static void assertNonexistentResourceResponseStep(ResourceResponse resourceResponse) {
        SoftAssertions softly = new SoftAssertions();

        Color color = resourceResponse.getData();

        softly.assertThat(color==null)
                .as("Resource is null");

    }
}
