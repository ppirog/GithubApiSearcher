package com.example.githubapisearcher.infrastructure.restcontroller.error;

import org.springframework.http.HttpStatus;


public record ErrorUserResponseDto(String message, HttpStatus httpStatus) {
}
