package com.example.helper.date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.example.helper.date.DateTimePattern.*;
import static com.example.helper.date.TimeZoneStringValues.TIME_ZONE_MOSCOW;
import static com.example.helper.date.TimeZoneStringValues.TIME_ZONE_UTC;
import static com.example.helper.logger.LogHelper.logExceptionAndFail;
import static com.example.helper.logger.MessageFormatter.format;
import static java.util.TimeZone.getTimeZone;


public class DateTimeUtil {
    private static final Logger LOGGER = LogManager.getLogger(DateTimeUtil.class);

    public static final String MDM_OBJECT_END_DATE_TIME = "2999-01-01T12:00:00.000";


    // region GET XMLGregorianCalendar

    /**
     * Получить XMLGregorianCalendar на текущий момент времени по Москве
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarNow() {
        XMLGregorianCalendar calendarDateTimeNow = null;

        ZonedDateTime zonedDateTime = Instant.now()
                .atZone(getMoscowZoneId());
        try {
            calendarDateTimeNow = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(zonedDateTime));
        } catch (DatatypeConfigurationException e) {
            String failMessage = format("DateError - Failed to get object XMLGregorianCalendar Now() <{}>",
                    zonedDateTime
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return calendarDateTimeNow;
    }

    /**
     * Получить XMLGregorianCalendar на текущий момент времени по Москве (БЕЗ времени)
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T00:00:00+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarNowWithoutTime() {
        XMLGregorianCalendar calendar = null;

        XMLGregorianCalendar xmlGregorianCalendarNow = getXMLGregorianCalendarNow();

        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    xmlGregorianCalendarNow.getYear(),
                    xmlGregorianCalendarNow.getMonth(),
                    xmlGregorianCalendarNow.getDay(),
                    0,
                    0,
                    0,
                    0,
                    xmlGregorianCalendarNow.getTimezone()
            );
        } catch (DatatypeConfigurationException e) {
            String failMessage = "DateError - Failed to get object XMLGregorianCalendar NowWithoutTime()";
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return calendar;
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением offset по указанному временному полю {@link Calendar }
     *
     * @param currentXMLGregorianCalendar - XMLGregorianCalendar for change
     * @param timeField                   - field number for <code>get</code> and <code>set</code> from Calendar class
     * @param offset                      - offset by field
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShifted(XMLGregorianCalendar currentXMLGregorianCalendar,
                                                                      int timeField,
                                                                      int offset
    ) {
        XMLGregorianCalendar newXmlGregorianCalendar = null;

        GregorianCalendar gregorianCalendar = currentXMLGregorianCalendar.toGregorianCalendar();
        gregorianCalendar.add(timeField, offset);

        try {
            newXmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            String failMessage = format("DateError - Failed to get Shifted XMLGregorianCalendar from <{}>",
                    currentXMLGregorianCalendar
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return newXmlGregorianCalendar;
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением amountMinutes минут
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedMinutes(XMLGregorianCalendar currentDate, int amountMinutes) {
        return getXMLGregorianCalendarShifted(currentDate, Calendar.MINUTE, amountMinutes);
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением amountHours часов
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedHours(XMLGregorianCalendar currentDate, int amountHours) {
        return getXMLGregorianCalendarShifted(currentDate, Calendar.HOUR, amountHours);
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением amountDays дней
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedDays(XMLGregorianCalendar currentDate, int amountDays) {
        return getXMLGregorianCalendarShifted(currentDate, Calendar.DAY_OF_YEAR, amountDays);
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением amountMonth месяцев
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedMonths(XMLGregorianCalendar currentDate, int amountMonth) {
        return getXMLGregorianCalendarShifted(currentDate, Calendar.MONTH, amountMonth);
    }

    /**
     * Получить XMLGregorianCalendar на текущий момент времени по Москве со смещением amountDays дней (БЕЗ времени)
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T00:00:00+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedDaysFromCurrentWithoutTime(int amountDays) {
        return getXMLGregorianCalendarShiftedDays(getXMLGregorianCalendarNowWithoutTime(), amountDays);
    }

    /**
     * Получить XMLGregorianCalendar на текущий момент времени по Москве со смещением amountMonth месяцев (БЕЗ времени)
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T00:00:00+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedMonthsFromCurrentWithoutTime(int amountMonth) {
        return getXMLGregorianCalendarShiftedMonths(getXMLGregorianCalendarNowWithoutTime(), amountMonth);
    }

    /**
     * Получить XMLGregorianCalendar по Москве с установленным offset по указанному временному полю {@link Calendar }
     *
     * @param currentXMLGregorianCalendar - XMLGregorianCalendar for change
     * @param timeField                   - field number for <code>get</code> and <code>set</code> from Calendar class
     * @param offset                      - offset by field
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-08T17:01:22+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarChanged(XMLGregorianCalendar currentXMLGregorianCalendar,
                                                                      int timeField,
                                                                      int offset
    ) {
        XMLGregorianCalendar newXmlGregorianCalendar = null;

        GregorianCalendar gregorianCalendar = currentXMLGregorianCalendar.toGregorianCalendar();
        gregorianCalendar.set(timeField, offset);

        try {
            newXmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            String failMessage = format("DateError - Failed to get Changed XMLGregorianCalendar from <{}>",
                    currentXMLGregorianCalendar
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return newXmlGregorianCalendar;
    }

    /**
     * Получить XMLGregorianCalendar по Москве со смещением на начало месяца
     *
     * @return XMLGregorianCalendar в формате yyyy-mm-ddThh:MM:ss+ (2017-12-01T00:00:00+03:00)
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarShiftedToMonthsFirstDayWithoutTime() {
        return getXMLGregorianCalendarChanged(
                getXMLGregorianCalendarNowWithoutTime(),
                Calendar.DATE,
                1
        );
    }

    /**
     * Преобразовать строку XMLGregorianCalendar в объект XMLGregorianCalendar
     *
     * @param stringDate строка, подаваемая на вход (2017-12-08T17:01:22+03:00)
     *                   подавать Московский часовой пояс
     * @return объект XMLGregorianCalendar
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarFromString(String stringDate) {
        XMLGregorianCalendar calendarDateTime = null;

        try {
            calendarDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(stringDate);
        } catch (DatatypeConfigurationException e) {
            String failMessage = format("DateError - Failed to get object XMLGregorianCalendar from String <{}>",
                    stringDate
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return calendarDateTime;
    }

    /**
     * Получить XMLGregorianCalendar из Data
     *
     * @param date - конвертируемая дата
     * @return - XMLGregorianCalendar
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
        XMLGregorianCalendar xmlGregorianCalendar = null;

        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            String failMessage = format("DateError - Failed to convert object Date <{}> to XMLGregorianCalendar",
                    date
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return xmlGregorianCalendar;
    }
    // endregion GET XMLGregorianCalendar


    // region GET DATE

    /**
     * Получить дату/время из Timestamp по Москве
     * <p>
     * использовать для обработки Timestamp из БД, которые хранятся по москве
     *
     * @return - Date
     */
    public static Date getDateTime(Timestamp timestamp) {

        if (timestamp != null) {
            return new Date(timestamp.getTime());
        }

        return null;
    }

    /**
     * Получить дату/время по Москве по паттерну {@link DateTimePattern }
     *
     * @return - Date
     */
    public static Date getDateTimeMoscow(DateTimePattern pattern) {
        Date date = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getValue());
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern.getValue());

        ZonedDateTime moscowZonedDateTime = LocalDateTime.now()
                .atZone(getCurrentZoneId())
                .withZoneSameInstant(getMoscowZoneId());

        String stringDate = formatter.format(moscowZonedDateTime);

        try {
            date = simpleFormatter.parse(stringDate);
        } catch (ParseException e) {
            String failMessage = format("DateError - Failed to get Date from String \"{}\" for getDateTimeMoscow",
                    stringDate
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return date;
    }

    /**
     * Получить дату/время по Москве (DATE_TIME_LONG_PATTERN)
     * <p>
     * "yyyy-MM-dd HH:mm:ss.SSS"
     *
     * @return - Date
     */
    public static Date getDateTimeMoscow() {
        return getDateTimeMoscow(DATE_TIME_LONG_PATTERN);
    }

    /**
     * Получить дату/время по Москве из локальной даты (DATE_TIME_PATTERN)
     * <p>
     * для Date, взытых в текущем часовом поясе, отличном от МСК, выполнится смещение под МСК
     * для Date, взятых в МСК часовом поясе дата/время не меняется
     *
     * @param toConvertDate - date toConvertDate in local time
     * @return - Date
     */
    public static Date getDateTimeMoscow(Date toConvertDate) {
        Date date = null;

        if (toConvertDate != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_LONG_PATTERN.getValue());
            SimpleDateFormat simpleFormatter = new SimpleDateFormat(DATE_TIME_LONG_PATTERN.getValue());

            ZonedDateTime moscowDateTime = toConvertDate.toInstant()
                    .atZone(getCurrentZoneId())
                    .withZoneSameInstant(getMoscowZoneId());

            String stringDate = formatter.format(moscowDateTime);

            try {
                date = simpleFormatter.parse(stringDate);
            } catch (ParseException e) {
                String failMessage = format("DateError - Failed to get Date from String \"{}\" (\"{}\") for getDateTimeMoscow",
                        toConvertDate,
                        stringDate
                );
                logExceptionAndFail(LOGGER, failMessage, e);
            }
        }

        return date;
    }

    /**
     * Получить Date из строки по паттерну {@link DateTimePattern }
     *
     * @param dateString      - строковое значение даты и времени
     * @param dateTimePattern - DateTimePattern
     * @return - Date
     */
    public static Date getDateTimeFromString(String dateString, DateTimePattern dateTimePattern) {
        Date dateTime = null;

        SimpleDateFormat formatter = new SimpleDateFormat(dateTimePattern.getValue());

        try {
            dateTime = formatter.parse(dateString);
        } catch (ParseException e) {
            String failMessage = format("DateError - Failed to convert string \"{}\" to date by format \"{}\"",
                    dateString,
                    formatter.toString()
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return dateTime;
    }

    /**
     * Получить Date из строки по паттерну "yyyy-MM-dd HH:mm:ss.SSS"
     *
     * @param dateString - строковое значение даты и времени
     * @return - Date
     */
    public static Date getDateTimeFromString(String dateString) {
        return getDateTimeFromString(dateString, DATE_TIME_LONG_PATTERN);
    }

    /**
     * Получить дату из строки со смещением в Московское время
     *
     * @param dateString      - строковое значение даты и времени
     * @param dateTimePattern - DateTimePattern
     * @return - Date
     */
    public static Date getDateTimeFromStringWithChangeTimeZoneToMoscow(String dateString, DateTimePattern dateTimePattern) {
        Date dateTime = null;

        SimpleDateFormat formatter = new SimpleDateFormat(dateTimePattern.getValue());
        formatter.setTimeZone(getTimeZone(TIME_ZONE_MOSCOW.getValue()));

        try {
            dateTime = formatter.parse(dateString);
        } catch (ParseException e) {
            String failMessage = format("DateError - Failed to convert string \"{}\" to date by format \"{}\"",
                    dateString,
                    formatter.toString()
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return dateTime;
    }

    /**
     * Получить Data дату/время из XMLGregorianCalendar
     *
     * @param xmlGregorianCalendar - конвертируемая дата
     * @return - Date
     */
    public static Date getDateTimeFromXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        Date date = null;

        try {
            date = xmlGregorianCalendar.toGregorianCalendar().getTime();
        } catch (Exception e) {
            String failMessage = format("DateError - Failed to convert XMLGregorianCalendar \"{}\" to Date",
                    xmlGregorianCalendar
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }

        return date;
    }

    /**
     * Получить Date дату/время по Москве из XmlGregorianCalendar
     *
     * @param xmlGregorianCalendar - xmlGregorianCalendar toConvertDate in local time
     * @return - Date
     */
    public static Date getDateTimeFromXMLGregorianCalendarToMoscowTimeZone(XMLGregorianCalendar xmlGregorianCalendar) {

        Date date = getDateTimeFromXMLGregorianCalendar(xmlGregorianCalendar);

        return getDateTimeMoscow(date);
    }

    /**
     * Получить Date дату/время по Москве из XmlGregorianCalendar
     *
     * @param xmlGregorianCalendar - xmlGregorianCalendar toConvertDate in local time
     * @return - Date
     */
    public static Date getDateTimeFromXMLGregorianCalendarToMoscowTimeZoneShiftedMinutes(XMLGregorianCalendar xmlGregorianCalendar, int amountMinutes) {
        return getDateTimeFromXMLGregorianCalendarToMoscowTimeZone(
                getXMLGregorianCalendarShiftedMinutes(xmlGregorianCalendar, amountMinutes)
        );
    }

    /**
     * Получить Date дату/время по Москве из XmlGregorianCalendar
     *
     * @param xmlGregorianCalendar - xmlGregorianCalendar toConvertDate in local time
     * @return - Date
     */
    public static Date getDateTimeFromXMLGregorianCalendarByUTCTimeZone(XMLGregorianCalendar xmlGregorianCalendar) {
        Date date = null;

        GregorianCalendar gregorianCalendar = xmlGregorianCalendar.toGregorianCalendar();
        gregorianCalendar.setTimeZone(getTimeZone(TIME_ZONE_UTC.getValue()));

        try {
            date = gregorianCalendar.getTime();
        } catch (Exception e) {
            String failMessage = format("DateError - Failed to convert XMLGregorianCalendar by UTC \"{}\" to Date",
                    xmlGregorianCalendar
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }
        return date;
    }

    /**
     * Получить дату "2999-01-01T12:00:00.000" для end_date в БД MDM
     * проставляется в БД как максимальая дата
     *
     * @return Date
     */
    public static Date getDateTimeMdmObjectEndDate() {
        return getDateTimeFromString(MDM_OBJECT_END_DATE_TIME, DATE_TIME_UTC_MEDIUM_PATTERN);
    }
    // endregion GET DATE


    // region GET STRING DATE

    /**
     * Получить строку из даты по формату паттерна {@link DateTimePattern}
     *
     * @param date    - дата
     * @param pattern - формат даты
     * @return - String
     */
    public static String getStringFromDateTime(Date date, DateTimePattern pattern) {
        String stringDate = null;

        SimpleDateFormat formatter = new SimpleDateFormat(pattern.getValue());

        try {
            stringDate = formatter.format(date);
        } catch (Exception e) {
            String failMessage = format("DateError - Failed to convert Date <{}> to String by pattern <{}>\n",
                    date,
                    formatter.toString()
            );
            logExceptionAndFail(LOGGER, failMessage, e);
        }
        return stringDate;
    }

    /**
     * Получить строку из даты в формате "yyyy-MM-dd HH:mm:ss.SSS"
     *
     * @param date - дата
     * @return - String
     */
    public static String getStringFromDateTime(Date date) {
        return getStringFromDateTime(date, DATE_TIME_LONG_PATTERN);
    }


    /**
     * Получить строку из текущей даты по Москве в формате "yyyy-MM-dd HH:mm:ss.SSS"
     *
     * @return - String
     */
    public static String getStringFromDateTimeNowToMoscowTimeZone() {
        return getStringFromDateTime(
                getDateTimeMoscow()
        );
    }

    /**
     * Получить строку из текущей даты по Москве UTC {DATE_TIME_UTC_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeUTC() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_UTC_PATTERN.getValue());

        return dateFormat.format(getDateTimeMoscow(DATE_TIME_UTC_PATTERN));
    }

    /**
     * Получить строку из текущей даты по Москве UTC Short {DATE_TIME_PATTERN_UTC_SHORT}
     *
     * @return - String
     */
    public static String getStringFromDateTimeUTCShort() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_UTC_SHORT_PATTERN.getValue());

        return dateFormat.format(getDateTimeMoscow(DATE_TIME_UTC_SHORT_PATTERN));
    }

    /**
     * Получить строку из текущей даты по Москве со смещением в amountDays UTC {DATE_TIME_UTC_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeUTCShiftedDays(int amountDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_UTC_PATTERN.getValue());

        Date date = DateUtils.addDays(getDateTimeMoscow(DATE_TIME_UTC_PATTERN), amountDays);

        return dateFormat.format(date);
    }

    /**
     * Получить строку из текущей даты по Москве со смещением в amountDays UTC Short {DATE_TIME_UTC_SHORT_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeUTCShortShiftedDays(int amountDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_UTC_SHORT_PATTERN.getValue());

        Date date = DateUtils.addDays(getDateTimeMoscow(DATE_TIME_UTC_SHORT_PATTERN), amountDays);

        return dateFormat.format(date);
    }

    /**
     * Получить строку из текущей даты по Москве со смещением в amountDays {DATE_TIME_PATTERN_UTC_SHORT}
     *
     * @return - String
     */
    public static String getStringFromDateTimeShiftedDays(int amountDays, DateTimePattern pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern.getValue());

        Date date = DateUtils.addDays(getDateTimeMoscow(pattern), amountDays);

        return dateFormat.format(date);
    }

    /**
     * Получить строку из текущей даты по Москве для TestRunner {DATE_TIME_FOR_JIRA_RUNNER_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeForTestRunner() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FOR_JIRA_RUNNER_PATTERN.getValue());

        return dateFormat.format(getDateTimeMoscow());
    }

    /**
     * Получить строку из текущей даты по Москве для Generator {DATE_TIME_FOR_GENERATOR_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeForGenerator() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FOR_GENERATOR_PATTERN.getValue());

        return dateFormat.format(getDateTimeMoscow(DATE_TIME_FOR_GENERATOR_PATTERN));
    }

    /**
     * Получить строку из текущей даты по Москве для Generator {DATE_TIME_FOR_GENERATOR_SHORT_PATTERN}
     *
     * @return - String
     */
    public static String getStringFromDateTimeForGeneratorShort() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FOR_GENERATOR_SHORT_PATTERN.getValue());

        return dateFormat.format(getDateTimeMoscow(DATE_TIME_FOR_GENERATOR_SHORT_PATTERN));
    }
    // endregion GET STRING DATE


    // region ZoneId

    /**
     * Получить текущую временную зону
     *
     * @return id (String)
     */
    public static ZoneId getCurrentZoneId() {
        return ZoneId.systemDefault();
    }

    /**
     * Получить московскую временную зону (Москва - Europe/Moscow)
     *
     * @return id (String)
     */
    public static ZoneId getMoscowZoneId() {
        return ZoneId.of(TIME_ZONE_MOSCOW.getValue());
    }
    // endregion ZoneId
}