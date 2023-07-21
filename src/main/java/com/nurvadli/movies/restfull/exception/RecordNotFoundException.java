package com.nurvadli.movies.restfull.exception;

/**
 * @author Nurvadli
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
