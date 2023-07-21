package com.nurvadli.movies.restfull.validator;

import com.nurvadli.movies.restfull.dto.MovieDto;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nurvadli
 */
public class DateTimeValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidInput() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Cerita Okatilo");
        movieDto.setDescription("Okatilo is called key");
        movieDto.setRating(8F);
        movieDto.setImage("");
        movieDto.setCreatedAt("2001-01-28 12:01:56");
        movieDto.setUpdatedAt("2001-01-28 12:01:56");
        List<ConstraintViolation<MovieDto>> violations = new ArrayList<>(validator.validate(movieDto));

        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidInputCreatedAt() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Cerita Tanah Jawa");
        movieDto.setDescription("Beakhir di pulau jawa");
        movieDto.setRating(5F);
        movieDto.setImage("");
        movieDto.setCreatedAt("2010-5-28 12:01");
        movieDto.setUpdatedAt("2010-07-28 12:01:56");
        List<ConstraintViolation<MovieDto>> violations = new ArrayList<>(validator.validate(movieDto));

        assertEquals(1, violations.size());
        Assert.assertEquals("Invalid dateTime value, rejected value: '2010-5-28 12:01'", violations.get(0).getMessage());
    }

}
