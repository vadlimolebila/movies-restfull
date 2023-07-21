package com.nurvadli.movies.restfull.dto;

import com.nurvadli.movies.restfull.validator.DateTime;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nurvadli
 */
@Data
public class MovieDto {

    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Float rating;

    private String image;

    @DateTime
    private String createdAt;

    @DateTime
    private String updatedAt;
}
