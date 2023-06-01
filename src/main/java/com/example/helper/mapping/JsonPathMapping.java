package com.example.helper.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static com.example.helper.date.TimeZoneStringValues.TIME_ZONE_MOSCOW;
import static com.example.helper.files.ReadExampleFileUtil.getFileByFileNameFromExampleFolder;
import static com.example.helper.logger.LogHelper.logExceptionAndFail;
import static com.example.helper.logger.MessageFormatter.format;
import static java.util.TimeZone.getTimeZone;

public class JsonPathMapping {
    private static final Logger LOGGER = LogManager.getLogger(JsonPathMapping.class);


    public static <T> T convertJsonPathToAnyObject(JsonPath jsonPath, Class<T> className) {
        T convertedObject = null;

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setTimeZone(getTimeZone(TIME_ZONE_MOSCOW.getValue()));

        try {
            convertedObject = mapper.readValue(jsonPath.prettify(), className);
        } catch (JsonProcessingException e) {
            String failMessage = format("JsonProcessingException - Failed to convert data from JsonPath to {}\n{}",
                    className.getName(),
                    jsonPath
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }
        return convertedObject;
    }


    public static <T> List<T> convertJsonPathToListOfAnyObject(JsonPath jsonPath, Class<T> className) {
        List<T> convertedObjectList = null;

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setTimeZone(getTimeZone(TIME_ZONE_MOSCOW.getValue()));

        TypeFactory factory = mapper.getTypeFactory();
        CollectionType listType = factory.constructCollectionType(List.class, className);

        try {
            convertedObjectList = mapper.readValue(jsonPath.prettify(), listType);
        } catch (JsonProcessingException e) {
            String failMessage = format("JsonProcessingException - Failed to convert data from JsonPath to List<{}>\n{}",
                    className.getName(),
                    jsonPath
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return convertedObjectList;
    }

    public static <T> T convertJsonFileToAnyObject(String fileName, Class<T> className) {
        T convertedObject = null;

        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setTimeZone(getTimeZone(TIME_ZONE_MOSCOW.getValue()));

        try {
            convertedObject = mapper.readValue(getFileByFileNameFromExampleFolder(fileName), className);
        } catch (JsonProcessingException e) {
            String failMessage = format("JsonProcessingException - Failed to convert data from JsonPath to {} from file \"{}\"",
                    className.getName(),
                    fileName
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        } catch (IOException e) {
            String failMessage = format("IOException - Failed to read data from file by path \"{}\"\n{}",
                    className.getName(),
                    fileName
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return convertedObject;
    }
}
