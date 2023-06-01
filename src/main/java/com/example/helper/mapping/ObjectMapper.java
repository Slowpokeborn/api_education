package com.example.helper.mapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.helper.logger.LogHelper.logExceptionAndFail;
import static com.example.helper.logger.MessageFormatter.format;

public class ObjectMapper {
    private static final Logger LOGGER = LogManager.getLogger(ObjectMapper.class);


    public static <T> List<T> convertObjectsListToTypedList(List<Object> objectList, Class<T> clazz) {
        List<T> classObjectList = new ArrayList<>();

        try {
            classObjectList = objectList.stream()
                    .map(clazz::cast)
                    .collect(Collectors.toList());
        } catch (ClassCastException e) {
            String failMessage = format("ClassCastException - Failed to cast data for class {}",
                    clazz
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return classObjectList;
    }
}
