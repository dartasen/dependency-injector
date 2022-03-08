package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.models.InjectOptions;

import java.util.EnumSet;

public abstract class AbstractInjector {

    public void inject(InjectOptions... options) {

    }

    public abstract void inject(EnumSet<InjectOptions> options);

}
