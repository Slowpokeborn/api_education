package com.example.services.reqres.response;

import com.example.helper.BaseObject;
import com.example.services.reqres.entities.Support;
import com.example.services.reqres.entities.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"data", "support"})
public class UsersResponse extends BaseObject {
    private User data;
    private Support support;
}
