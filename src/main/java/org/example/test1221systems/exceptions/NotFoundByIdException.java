package org.example.test1221systems.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public <T> NotFoundByIdException(Class<T> clazz, Long id){
        super(clazz.getSimpleName() + " by id=" + id + " not found");
    }
}

