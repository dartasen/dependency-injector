package me.dartasen.dependencyinjector.test.injectors.models;

import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;

@Component(lifecycle = LifeCycle.SINGLETON)
public class AbusedChildChildClass {

    public AbusedClass abusedClass;

    @Autowired
    public void setAbusedClass(AbusedClass abusedClass) {
        this.abusedClass = abusedClass;
    }

    public void setAbusedClass2(AbusedClass abusedClass) {

    }
}
