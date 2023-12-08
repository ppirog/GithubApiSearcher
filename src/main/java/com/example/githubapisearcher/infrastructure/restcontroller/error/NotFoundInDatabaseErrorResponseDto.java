package com.example.githubapisearcher.infrastructure.restcontroller.error;

import org.springframework.http.HttpStatus;

public record NotFoundInDatabaseErrorResponseDto(String message, HttpStatus httpStatus) {
}
