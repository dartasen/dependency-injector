package me.dartasen.dependencyinjector.injectors.models;

import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.annotations.Component;

@Component(lifecycle = LifeCycle.SINGLETON)
public class AbusedChildClass {

    public AbusedChildClass() {
    }

    public void b() {

    }

}
