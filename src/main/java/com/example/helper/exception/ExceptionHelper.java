package com.example.helper.exception;

import static com.example.helper.logger.MessageFormatter.format;

public class ExceptionHelper {

    /**
     * Получить форматированную строку из StackTraceElement
     *
     * @param e - Throwable
     * @return - formatted String
     */
    public static String convertStackTraceToString(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement element : e.getStackTrace()) {
            stringBuilder.append("\t");
            stringBuilder.append(element.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Получить форматированные данные по Exception
     *
     * @param e - Throwable
     * @return - formatted String
     */
    public static String getInfoFromException(Throwable e) {
        return format("{}\n{}",
                e.getMessage(),
                convertStackTraceToString(e)
        );
    }
}
