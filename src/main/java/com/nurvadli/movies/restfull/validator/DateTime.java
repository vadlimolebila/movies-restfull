package com.nurvadli.movies.restfull.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Nurvadli
 */
@Documented
@Constraint(validatedBy = {DateTimeValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateTime {
    String message() default "Invalid dateTime value, rejected value: '${validatedValue}'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
