package com.nurvadli.movies.restfull.utils;

import com.nurvadli.movies.restfull.exception.BusinessException;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Nurvadli
 */
public class DateTimeUtilTest {

    @Test
    public void testFormatDateTime() {
        String dateTimeString = "2023-07-20 13:43:00";
        OffsetDateTime offsetDateTime = DateTimeUtil.toDateTimeFormat(dateTimeString);

        String formattedDateTimeString = DateTimeUtil.toStringFormat(offsetDateTime);

        assertEquals(formattedDateTimeString, dateTimeString);
    }

    @Test
    public void testFormatDateTimeInvalid() {
        String dateTimeString = "2023/07/20 13:43:00";
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            DateTimeUtil.toDateTimeFormat(dateTimeString);
        });
    }
}
