package me.dartasen.dependencyinjector.models.exceptions;

public class InvalidBeanException extends RuntimeException {

    public InvalidBeanException(String message) {
        super(message);
    }

}
