package me.dartasen.dependencyinjector.models.exceptions;

public class DependencyCircleException extends RuntimeException {

    public DependencyCircleException(String message) {
        super(message);
    }

}
