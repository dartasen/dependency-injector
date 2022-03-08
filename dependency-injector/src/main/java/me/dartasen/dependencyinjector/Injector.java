package me.dartasen.dependencyinjector;

import me.dartasen.dependencyinjector.injectors.AbstractInjector;
import me.dartasen.dependencyinjector.models.IContainer;
import me.dartasen.dependencyinjector.models.InjectOptions;

import java.util.EnumSet;

public class Injector extends AbstractInjector {

    private final IContainer container;

    public Injector(IContainer container) {
        this.container = container;
    }

    public void inject(EnumSet<InjectOptions> options) {
        
    }

}
