package com.example.githubapisearcher.infrastructure.restcontroller.error;

import com.example.githubapisearcher.infrastructure.restcontroller.GithubMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;


public record ErrorUserResponseDto(String message, HttpStatus httpStatus) {
}
