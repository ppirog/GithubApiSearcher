package com.example.githubapisearcher.infrastructure.restcontroller.error;

import org.springframework.http.HttpStatus;

public record WrongHeaderErrorResponseDto(String message, HttpStatus httpStatus) {
}
