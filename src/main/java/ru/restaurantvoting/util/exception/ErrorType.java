package ru.restaurantvoting.util.exception;

public enum ErrorType {
    APP_ERROR("application error"),
    DATA_NOT_FOUND("data not found"),
    DATA_ERROR("data error"),
    VALIDATION_ERROR("validation error"),
    WRONG_REQUEST("wrong request");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
