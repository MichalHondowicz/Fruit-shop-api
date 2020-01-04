package com.michon.fruitshopapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class TestRestControllerExtensionMethods {

    public static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
