package com.sippet.domain.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeUtils {
    public static LocalDateTime startToday() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }


    public static LocalDateTime endToday() {
        return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
    }
}
