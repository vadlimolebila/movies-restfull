package com.nurvadli.movies.restfull.utils;

import com.nurvadli.movies.restfull.exception.BusinessException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Nurvadli
 */
public final class DateTimeUtil {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toStringFormat(OffsetDateTime input) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return fmt.format(input);
    }

    public static OffsetDateTime toDateTimeFormat(String input) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        try{
            return LocalDateTime.parse(input, fmt)
                    .atZone(ZoneId.of("Asia/Jakarta"))
                    .toOffsetDateTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Invalid format date input: "+ input);
        }
    }
}
