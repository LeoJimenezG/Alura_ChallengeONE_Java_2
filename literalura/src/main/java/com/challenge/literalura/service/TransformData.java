package com.challenge.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformData{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T transformData(String json, Class<T> classType) {
        try {
            // Transform the json using a class model
            return objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            // Throw error if the json and class don't match
            throw new RuntimeException(e);
        }
    }
}
