package com.nurvadli.movies.restfull.validator;

import com.nurvadli.movies.restfull.utils.DateTimeUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Nurvadli
 */
public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasLength(value) && isValidDate(value);
    }

    private static boolean isValidDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.DATE_TIME_FORMAT);
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
