package com.korbiak.demo.handler;

import com.korbiak.demo.handler.error.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class IllegalArgumentExceptionHandlerApi extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleIllegalArgumentExceptions(IllegalArgumentException exception) {
        return getApiError(exception);
    }

    private ApiError getApiError(Exception e) {
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .subErrors(new ArrayList<>())
                .build();
    }
}
