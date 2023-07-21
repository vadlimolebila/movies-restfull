package com.nurvadli.movies.restfull.exception;

import com.nurvadli.movies.restfull.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Nurvadli
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ResponseDto> handleBadRequest(RuntimeException ex, WebRequest request) {
        log.error("BAD-REQUEST: {}", ex.getMessage());
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(40011)
                        .message("")
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto> handleArgumentNotValid(MethodArgumentNotValidException exception) {
        String message = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst().get().getDefaultMessage();

        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(40012)
                        .message(message)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RecordAlreadyExistException.class)
    public ResponseEntity<ResponseDto> handleRecordAlreadyExist(RecordAlreadyExistException exception) {

        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(40013)
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<ResponseDto> handleRecordNotFound(RecordNotFoundException exception) {

        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(40014)
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ResponseDto> handleTechnicalBusinessError(BusinessException exception) {

        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(40014)
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
