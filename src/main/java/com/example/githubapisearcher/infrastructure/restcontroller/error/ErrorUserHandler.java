package com.example.githubapisearcher.infrastructure.restcontroller.error;

import com.example.githubapisearcher.domain.model.exception.WrongExpectedDataFormatInHeaderException;
import com.example.githubapisearcher.domain.model.exception.WrongUsernameException;
import com.example.githubapisearcher.infrastructure.restcontroller.GithubUserRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice(assignableTypes = GithubUserRestController.class)
public class ErrorUserHandler {


    @ExceptionHandler(WrongUsernameException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUserResponseDto handleException(WrongUsernameException exception) {
        final String clientMessage = exception.getMessage();
        final HttpStatus badRequest = HttpStatus.NOT_FOUND;
        log.error("messaage to client has been sent: " + clientMessage + " http status: " + badRequest);
        return new ErrorUserResponseDto(clientMessage, badRequest);
    }

    @ExceptionHandler(WrongExpectedDataFormatInHeaderException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<WrongHeaderErrorResponseDto> handleException(WrongExpectedDataFormatInHeaderException exception) {
        final HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        final String headerInfo = "wrong header format. Set to application/json";
        log.error(headerInfo + " " + exception.getMessage() + " http status: " + httpStatus);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        final WrongHeaderErrorResponseDto wrongHeaderErrorResponseDto = new WrongHeaderErrorResponseDto(headerInfo, httpStatus);

        return new ResponseEntity<>(wrongHeaderErrorResponseDto, httpHeaders, httpStatus);
    }

}
