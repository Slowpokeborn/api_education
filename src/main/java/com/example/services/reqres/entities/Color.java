package com.example.services.reqres.entities;

import com.example.helper.BaseObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Color extends BaseObject {
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}
