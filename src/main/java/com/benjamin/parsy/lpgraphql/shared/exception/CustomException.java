package com.benjamin.parsy.lpgraphql.shared.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private final ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getFormattedMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getCode();
    }

    public String getDescription() {
        return errorMessage.getDescription();
    }

}