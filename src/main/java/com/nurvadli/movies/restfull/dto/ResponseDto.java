package com.nurvadli.movies.restfull.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nurvadli
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Integer code;
    private String message;
}
