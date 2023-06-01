package com.example.helper.logger;

import junit.framework.AssertionFailedError;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.helper.exception.ExceptionHelper.getInfoFromException;
import static com.example.helper.logger.MessageFormatter.format;
import static org.junit.jupiter.api.Assertions.fail;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogHelper {

    /**
     * Преобразует "массив" параметров переменной длины в список.
     *
     * @param logArgs - аргументы для передачи в лог
     * @return - String
     */
    public static List<Object> getArray(Object... logArgs) {
        return new ArrayList<>(Arrays.asList(logArgs));
    }


    /**
     * Метод вывода ошибки в лог и проброса JUnit Assertions.fail с сообщением из Exception
     * В лог выводится полная информация - message + stackTrace
     * В fail передается только message
     *
     * @param LOGGER      - логгер класса, в котором возник Exception
     * @param failMessage - пользовательское сообщение об ошибке
     * @param e           - обрабатываемый Exception
     */
    public static void logExceptionAndFail(Logger LOGGER, String failMessage, Exception e) throws AssertionFailedError {
        LOGGER.error(format("{}\n{}", failMessage, getInfoFromException(e)));
        fail(format("{}\n{}", failMessage, e.getMessage()));
    }

    public static void logExceptionAndFail(Logger LOGGER, String failMessage) throws AssertionFailedError {
        LOGGER.error(failMessage);
        fail(failMessage);
    }
}