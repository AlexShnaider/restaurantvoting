package ru.restaurantvoting.util.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {

    private final ErrorType type;
    private final String message;
    private final HttpStatus httpStatus;
    private final String[] args;

    public ApplicationException(String message, HttpStatus httpStatus) {
        this(ErrorType.APP_ERROR, message, httpStatus);
    }

    public ApplicationException(ErrorType type, String message, HttpStatus httpStatus, String... args) {
        super(String.format("type=%s, message=%s, args=%s", type, message, Arrays.toString(args)));
        this.type = type;
        this.message = message;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String[] getArgs() {
        return args;
    }

}
