package com.example.helper.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static com.example.helper.date.DateTimePattern.DATE_TIME_LONG_PATTERN;
import static com.example.helper.date.TimeZoneStringValues.TIME_ZONE_MOSCOW;
import static com.example.helper.logger.LogHelper.logExceptionAndFail;
import static java.util.TimeZone.getTimeZone;
import static org.slf4j.helpers.MessageFormatter.arrayFormat;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageFormatter {
    private static final Logger LOGGER = LogManager.getLogger(MessageFormatter.class);


    /**
     * Получить строку String комментария
     *
     * @param message - текст сообщения с расставленными сслыками на параметры {}
     * @param args    - параметры для текста сообщения
     * @return - String
     */
    public static String format(String message, Object... args) {
        return arrayFormat(message, args).getMessage();
    }

    /**
     * Получить форматированную XML из неформатированной (в строку)
     *
     * @param input  - input XML
     * @param indent - indent-number
     * @return - output XML
     */
    public static String getXmlStringInPrettyFormat(String input, int indent) {

        Source xmlInput = new StreamSource(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
        } catch (Exception e) {
            String failMessage = "Exception by getXmlStringInPrettyFormat";
            logExceptionAndFail(LOGGER, failMessage, e);
        }
        return xmlOutput.getWriter().toString();
    }

    /**
     * Получить форматированную XML из неформатированной (в строку)
     * с установленным отступом indent = 2
     *
     * @param input - input XML
     * @return - output XML
     */
    public static String getXmlStringInPrettyFormat(String input) {
        return getXmlStringInPrettyFormat(input, 2);
    }

    /**
     * Получить строку JSON формата из объекта
     * формат дат согласно - DATE_TIME_PATTERN
     * временная зона дат - оригинальная
     * ИСПОЛЬЗОВАТЬ - для объектом с датами без временной зоны - вывод в оригинале
     *
     * @param object - конвертируемый объект
     * @return - String
     */
    public static String toJsonFormat(Object object) {
        return toJsonConverter(object, null);
    }


    // region PRIVATE UTILS

    /**
     * Получить строку JSON формата из объекта
     * формат дат согласно - DATE_TIME_PATTERN
     * временная зона дат - временная зона (TIME_ZONE_MOSCOW) или null
     *
     * @param object   - конвертируемый объект
     * @param timeZone - временная зона или null
     * @return - String
     */
    private static String toJsonConverter(Object object, TimeZone timeZone) {
        String json = "";

        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_LONG_PATTERN.getValue());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        if (timeZone != null) {
            dateFormat.setTimeZone(getTimeZone(TIME_ZONE_MOSCOW.getValue()));
        }
        //mapper.setSerializationInclusion(NON_NULL); // убирает все NULL поля при сереализации

        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            String failMessage = "Failed to convert to JSON (toJsonFormatForObjectWithXMLGregorianCalendar method)";
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return json;
    }
    // endregion PRIVATE UTILS
}