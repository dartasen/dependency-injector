package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.Injector;

public abstract class AbstractDependencyInjector {

    /**
     * Required to implement a specific injection protocol for a given type
     * @param type Type class we wanna inject, used to scan fields, constructors, annotations, ...
     * @param target Valid instance of the given type
     * @param injector Injector used to retrieve dependencies in the container
     */
    public abstract void inject(Class<?> type, Object target, Injector injector);

}