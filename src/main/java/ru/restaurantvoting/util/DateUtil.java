package ru.restaurantvoting.util;

import java.time.LocalDate;

public class DateUtil {

    public static final LocalDate MIN_DATE = LocalDate.of(0,1,1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000,1,1);

    private DateUtil() {
    }

    public static LocalDate orElse(LocalDate date, LocalDate defaultValue) {
        return date == null ? defaultValue : date;
    }
}
