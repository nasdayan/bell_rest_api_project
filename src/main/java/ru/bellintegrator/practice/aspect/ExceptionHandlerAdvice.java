package ru.bellintegrator.practice.aspect;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exception.ItemNotFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(Exception exception) {
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handle(ItemNotFoundException exception) {
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseError handle(MethodArgumentNotValidException exception) {
        return new ResponseError(exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; ")));
    }
}
