package com.example.helper;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.helper.files.FilesHelper.readFileToInputStream;
import static com.example.helper.logger.MessageFormatter.getXmlStringInPrettyFormat;
import static java.text.MessageFormat.format;

public class AllureAttachment {
    private final static Logger LOGGER = LogManager.getLogger(AllureAttachment.class);

    private static final String APPLICATION_XML_TYPE = "application/xml";
    private static final String APPLICATION_ZIP_TYPE = "application/ZIP";
    private static final String TEXT_PLAIN_TYPE = "text/plain";
    private static final String TEXT_JSON_TYPE = "text/json";
    private static final String TEXT_XML_TYPE = "text/xml";


    // region TEXT
    public static void attachToAllureTextMessage(String attachName, String message) {
        Allure.addAttachment(
                attachName,
                TEXT_PLAIN_TYPE,
                message
        );
    }


    public static void attachToAllureRequestParameters(String url, String login) {
        attachToAllureTextMessage(
                "Request parameters",
                format("URL = {}\nLOGIN = {}", url, login)
        );
    }
    // endregion TEXT


    // region Object (toJson)
    public static void attachToAllureObjectWithToJson(String attachName, Object object) {
        Allure.addAttachment(
                attachName,
                TEXT_JSON_TYPE,
                object.toString()
        );
    }


    public static void attachToAllureObjectWithToJson(Object object) {
        attachToAllureObjectWithToJson(
                object.getClass().getSimpleName(),
                object
        );
    }
    // endregion Object (toJson)


    // region JSON
    public static void attachToAllureJsonPathWithPrettify(String attachName, JsonPath jsonPath) {
        Allure.addAttachment(
                attachName,
                TEXT_JSON_TYPE,
                jsonPath.prettify()
        );
    }


    public static void attachToAllureJsonPathRequest(String requestUrl, JsonPath jsonPath) {
        attachToAllureJsonPathWithPrettify(
                format("Request body - \"{}\"", requestUrl),
                jsonPath
        );
    }

    public static void attachToAllureJsonPathResponse(String requestUrl, JsonPath jsonPath) {
        attachToAllureJsonPathWithPrettify(
                format("Response body - \"{}\"", requestUrl),
                jsonPath
        );
    }
    // endregion JSON


    // region XML
    public static void attachToAllureXMLWithPrettify(String attachName, String xmlString) {
        Allure.addAttachment(
                attachName,
                TEXT_XML_TYPE,
                getXmlStringInPrettyFormat(xmlString)
        );
    }
    // endregion XML


    // region SOAP message
    public static void attachToAllureSoapMessageWithPrettify(String attachName, String message) {
        Allure.addAttachment(
                attachName,
                APPLICATION_XML_TYPE,
                getXmlStringInPrettyFormat(message)
        );
    }

    public static void attachToAllureSoapMessageHeaders(String attachName, String headers) {
        Allure.addAttachment(
                attachName,
                TEXT_PLAIN_TYPE,
                headers
        );
    }
    // endregion SOAP message


    public static void attachToAllureZipFile(String attachFilePath) {
        Allure.addAttachment(
                attachFilePath,
                APPLICATION_ZIP_TYPE,
                readFileToInputStream(attachFilePath),
                "zip"
        );
    }
}