package com.sippet.domain.util;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class LocalDateTimeUtils {
    /**
     * Get today date
     *
     * @return today date
     */
    public static LocalDateTime getToady() {
        return LocalDateTime.now();
    }

    /**
     * Calculate to get between period of two dates
     *
     * @param latestDate
     * @param todayDate
     * @return between period of two date
     */
    public static Long calculateDays(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(latestDate.toLocalDate(), todayDate.toLocalDate());
    }
}
