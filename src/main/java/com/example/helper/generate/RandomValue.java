package com.example.helper.generate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomValue {
    private static final Logger LOGGER = LogManager.getLogger(RandomValue.class);


    // region int value
    public static int getRandomDigits() {
        return (int) (1 + Math.random() * 8);
    }

    public static int getRandomNegativeInt() {
        Random random = new Random();

        int limit = -1 * random.nextInt(Integer.MAX_VALUE);

        LOGGER.debug("Сгенерировано случайное отрицательное значение - {}", limit);
        return limit;
    }

    public static long getRandomLongValue(long start, long end) {
        return ThreadLocalRandom.current().nextLong(start, end);
    }
    // endregion int value


    // region String value
    public static String getRandomLongDigitsString(int stringLength) {
        StringBuilder builder = new StringBuilder();

        for (int i = -1; i < stringLength; i++) {
            builder.append(getRandomDigits());
        }

        return builder.toString();
    }

    public static String getRandomStringAmount(int startValue, int endValue) {
        DecimalFormat formatter = new DecimalFormat("#0.00");

        double randomValue = startValue + Math.random() * endValue;
        double res = Math.floor(randomValue * 10) / 10;

        return formatter.format(res);
    }
    // endregion String value
}
