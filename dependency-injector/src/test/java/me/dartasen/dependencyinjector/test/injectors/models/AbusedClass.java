package me.dartasen.dependencyinjector.test.injectors.models;

import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;

@Component(lifecycle = LifeCycle.SINGLETON)
public class AbusedClass {

    public AbusedClass () { }

    @Autowired
    public AbusedChildClass abusedClass;

    public AbusedChildClass abusedClassNotWired;

    public void a() {

    }

}

