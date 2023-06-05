package com.example.api.tests.reqres.users;

import com.example.services.reqres.response.ResourceListResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.filters.TagStore.POSITIVE;
import static com.example.api.steps.reqes.resource.ResourceListAssertionSteps.assertResourceListResponseStep;
import static com.example.api.steps.reqes.resource.ResourceListSteps.getResourceListStep;

@Epic("API. Методы работы с resource")
@Feature("Метод \"/api/unknown\" - получение списка ресурса")
public class ResourceListTest {

    @Test
    @Tag(POSITIVE)
    @DisplayName("Получение ресурса")
    @Description("Получение существующего списка ресурса")
    public void getExistentResourceTest() {

        ResourceListResponse resourceListStep = getResourceListStep();
        assertResourceListResponseStep(resourceListStep);
    }

}
