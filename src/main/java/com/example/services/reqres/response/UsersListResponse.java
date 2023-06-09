package com.example.services.reqres.response;

import com.example.helper.BaseObject;
import com.example.services.reqres.entities.Support;
import com.example.services.reqres.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"page", "per_page", "total", "total_pages", "data", "support"})
public class UsersListResponse extends BaseObject {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<User> data;
    private Support support;
}
