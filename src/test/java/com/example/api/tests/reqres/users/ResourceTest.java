package com.example.api.tests.reqres.users;

import com.example.services.reqres.response.ResourceResponse;
import com.example.services.reqres.response.UsersResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.filters.TagStore.NEGATIVE;
import static com.example.api.filters.TagStore.POSITIVE;
import static com.example.api.steps.reqes.resource.ResourceAssertionsSteps.assertNonexistentResourceResponseStep;
import static com.example.api.steps.reqes.resource.ResourceAssertionsSteps.assertResourceResponseStep;
import static com.example.api.steps.reqes.resource.ResourceSteps.getNoResourceStep;
import static com.example.api.steps.reqes.resource.ResourceSteps.getResourceStep;


@Epic("API. Методы работы с пользователями")
@Feature("Метод \"/api/unknown/{id}\" - получение ресурса по Id")
public class ResourceTest {

    @Test
    @Tag(POSITIVE)
    @DisplayName("Получение существующего ресурса")
    @Description("Получение существующего ресурса")
    public void getResourceExistedTest() {
        int id = 2;

        ResourceResponse resourceResponse = getResourceStep(id);
        assertResourceResponseStep(resourceResponse);
    }

    @Test
    @Tag(NEGATIVE)
    @DisplayName("Получение несуществующего ресурса")
    @Description("Получение несуществующего ресурса")
    public void getResourceNonexistentTest() {
        int id = 23;

        ResourceResponse resourceResponse = getNoResourceStep(id);
        assertNonexistentResourceResponseStep(resourceResponse);
    }
}

