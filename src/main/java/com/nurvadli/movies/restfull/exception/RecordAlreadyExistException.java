package com.nurvadli.movies.restfull.exception;

/**
 * @author Nurvadli
 */
public class RecordAlreadyExistException extends RuntimeException {

    public RecordAlreadyExistException() {
        super();
    }

    public RecordAlreadyExistException(String message) {
        super(message);
    }
}
