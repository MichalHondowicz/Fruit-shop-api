package com.michon.fruitshopapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNameException extends RuntimeException {

    public CategoryNameException(String name) {
        super(String.format("Category name: %s already exists", name));
    }
}

