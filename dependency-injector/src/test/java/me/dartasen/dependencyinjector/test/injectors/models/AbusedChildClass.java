package me.dartasen.dependencyinjector.test.injectors.models;

import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.annotations.Component;

@Component(lifecycle = LifeCycle.SINGLETON)
public class AbusedChildClass {

    public AbusedChildClass() {
    }

    public void b() {

    }

}
