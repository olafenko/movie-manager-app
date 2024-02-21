package com.olafenko.moviemanagerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    HttpStatus conflict = HttpStatus.CONFLICT;
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    @ExceptionHandler(value = {MovieAlreadyExistsException.class})
    public ResponseEntity<Object> handleMovieAlreadyExistsException(MovieAlreadyExistsException e){


        ApiException exception = new ApiException(e.getMessage(), conflict, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exception, conflict);
    }

    @ExceptionHandler(value = {WrongMovieDescriptionException.class})
    public ResponseEntity<Object> handleWrongMovieDescriptionException(WrongMovieDescriptionException e){
        ApiException exception = new ApiException(e.getMessage(), conflict, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exception,conflict );
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ApiException z = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(z,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {WrongPasswordException.class})
    public ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException e) {
        ApiException z = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(z,badRequest);

    }

    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity<Object> handleEmailTakenException(EmailTakenException e){
        ApiException z = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(z, badRequest);

    }





}
