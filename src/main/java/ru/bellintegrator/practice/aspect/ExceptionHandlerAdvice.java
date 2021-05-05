package ru.bellintegrator.practice.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class.getName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(Exception exception) {
        logger.error("Код ошибки: " + HttpStatus.INTERNAL_SERVER_ERROR + ". " + exception.getMessage());
        return new ResponseError("Код ошибки: " + HttpStatus.INTERNAL_SERVER_ERROR + ". " + exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handle(ItemNotFoundException exception) {
        logger.error("Код ошибки: " + HttpStatus.NOT_FOUND + ". " + exception.getMessage());
        return new ResponseError("Код ошибки: " + HttpStatus.NOT_FOUND + ". " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseError handle(MethodArgumentNotValidException exception) {
        logger.error("Код ошибки: " + HttpStatus.BAD_REQUEST + ". " + exception.getMessage());
        return new ResponseError("Код ошибки: " + HttpStatus.BAD_REQUEST + ". " + exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; ")));
    }
}
