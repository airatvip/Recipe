package com.airat.recipe.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectInputException extends RuntimeException{
    public IncorrectInputException (String message) {
        super(message);
    }
}

