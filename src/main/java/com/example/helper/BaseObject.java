package com.example.helper;

import static com.example.helper.logger.MessageFormatter.toJsonFormat;

public class BaseObject {

    @Override
    public String toString() {
        return toJsonFormat(this);
    }
}
