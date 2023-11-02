package com.example.githubapisearcher.domain.model.exception;

public class WrongUsernameException extends RuntimeException {

    public WrongUsernameException(String message) {
        super(message);
    }
}
