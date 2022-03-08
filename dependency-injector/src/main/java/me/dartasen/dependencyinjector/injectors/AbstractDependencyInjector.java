package me.dartasen.dependencyinjector.injectors;

public abstract class AbstractDependencyInjector {

    protected final Class<?> type;

    public AbstractDependencyInjector(Class<?> type) {
        this.type = type;
    }

    public abstract void inject(Object target);
}
