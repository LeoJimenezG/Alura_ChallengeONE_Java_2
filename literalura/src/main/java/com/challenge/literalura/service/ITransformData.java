package com.challenge.literalura.service;

public interface ITransformData {
    <T> T transformData(String json, Class<T> classType);
}
