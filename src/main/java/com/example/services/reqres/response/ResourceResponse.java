package com.example.services.reqres.response;

import com.example.helper.BaseObject;
import com.example.services.reqres.entities.Color;
import com.example.services.reqres.entities.Support;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"data", "support"})
public class ResourceResponse extends BaseObject {
    private Color data;
    private Support support;
}
