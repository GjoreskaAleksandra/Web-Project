package com.example.wpproject.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrugNotFoundException extends RuntimeException{

    public DrugNotFoundException(Long id) {
        super(String.format("Drug with id: %d was not found", id));
    }
}

