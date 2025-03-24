package org.example.test1221systems.exceptions;

public class InvalidValueException extends RuntimeException {
    public <T> InvalidValueException(Class<T> clazz) {
        super(clazz.getSimpleName());
    }
}