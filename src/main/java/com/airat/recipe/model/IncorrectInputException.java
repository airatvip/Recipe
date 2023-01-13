package com.airat.recipe.model;

public class IncorrectInputException extends RuntimeException{
    public IncorrectInputException (String message) {
        super(message);
    }
}

