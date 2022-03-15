package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.Injector;

public abstract class AbstractDependencyInjector {

    public abstract void inject(Class<?> type, Object target, Injector injector);

}
