package com.example.services.reqres.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonPropertyOrder({"name", "job", "id", "createdAt", "updatedAt"})

public class UserCreated {
    private String name;
    private String job;
    private String id;
    private Date createdAt;
    private String updatedAt;
    private String currentTime;


}
