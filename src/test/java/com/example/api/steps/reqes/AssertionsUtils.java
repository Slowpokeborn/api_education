package com.example.api.steps.reqes;

import com.example.services.reqres.entities.Support;
import com.example.services.reqres.entities.UserToken;
import com.example.services.reqres.response.ResourceListResponse;
import com.example.services.reqres.response.ResourceResponse;
import com.example.services.reqres.response.UsersListResponse;
import com.example.services.reqres.response.UsersResponse;
import org.assertj.core.api.SoftAssertions;

import static com.example.services.reqres.entities.example.SupportExample.SUPPORT_EXAMPLE;

public class AssertionsUtils {

    public static void assertSupport(SoftAssertions softly, UsersListResponse usersListResponse) {

        assertSupport(softly, usersListResponse.getSupport());
    }

    public static void assertSupport(SoftAssertions softly, UsersResponse usersResponse) {

        assertSupport(softly, usersResponse.getSupport());
    }

    public static void assertSupport(SoftAssertions softly, ResourceListResponse resourceListResponse) {

        assertSupport(softly, resourceListResponse.getSupport());
    }

    public static void assertSupport(SoftAssertions softly, ResourceResponse resourceResponse) {

        assertSupport(softly, resourceResponse.getSupport());
    }



    // region private UTILS
    private static void assertSupport(SoftAssertions softly, Support support) {

        Support supportExample = SUPPORT_EXAMPLE.getSupport();

        softly.assertThat(support.getUrl())
                .as("Assert Support URL")
                .isEqualTo(supportExample.getUrl());
        softly.assertThat(support.getText())
                .as("Assert Support text")
                .isEqualTo(supportExample.getText());
    }
    // endregion private UTILS
}
