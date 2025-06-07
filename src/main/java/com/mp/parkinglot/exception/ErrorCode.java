package com.mp.parkinglot.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_USER_ID(HttpStatus.UNAUTHORIZED, "Invalid user id"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid or expired token"),
    SIGN_UP_REQUIRED(HttpStatus.ACCEPTED, "Need Sign-up"),
    FAILED_API_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "API request failed");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

