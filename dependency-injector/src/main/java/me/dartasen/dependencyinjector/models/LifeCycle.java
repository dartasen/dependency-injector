package me.dartasen.dependencyinjector.models;

/**
 * Used to specify how we want to manage lifecycle of our component
 */
public enum LifeCycle {

    /**
     * The container will store a single instance of the given class
     */
    SINGLETON,

    /**
     * The container will instanciate a new instance everytime
     */
    INSTANCE,

    /**
     * Unknown lifecycle
     */
    UNKNOWN
}
