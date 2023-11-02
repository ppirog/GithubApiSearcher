package com.example.githubapisearcher.domain.model.exception;

public class WrongExpectedDataFormatInHeaderException extends RuntimeException {
    public WrongExpectedDataFormatInHeaderException(String message) {
        super(message);
    }
}
