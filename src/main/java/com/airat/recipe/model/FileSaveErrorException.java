package com.airat.recipe.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public class FileSaveErrorException extends RuntimeException{
        public FileSaveErrorException  (String message) {
            super (message);
        }
    }

