package me.dartasen.dependencyinjector.models.exceptions;

public class BeanCreationException extends RuntimeException {

    public BeanCreationException(String message) {
        super(message);
    }

}
