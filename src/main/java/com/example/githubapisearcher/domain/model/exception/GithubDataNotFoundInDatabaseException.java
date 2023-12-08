package com.example.githubapisearcher.domain.model.exception;

public class GithubDataNotFoundInDatabaseException extends RuntimeException{
    public GithubDataNotFoundInDatabaseException(String message){
        super(message);
    }
}
