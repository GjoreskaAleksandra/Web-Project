package com.example.wpproject.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class DrugAlreadyInCartException extends RuntimeException{
    public DrugAlreadyInCartException(Long id, String username){
        super(String.format("Drug with id: %d already exists in cart for user with username %s", id, username));
    }
}
