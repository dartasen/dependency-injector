package me.dartasen.dependencyinjector.test.injectors;

import me.dartasen.dependencyinjector.Container;
import me.dartasen.dependencyinjector.Injector;
import me.dartasen.dependencyinjector.injectors.AnnotatedFieldInjector;
import me.dartasen.dependencyinjector.test.injectors.models.AbusedChildClass;
import me.dartasen.dependencyinjector.test.injectors.models.AbusedClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotatedFieldInjectorTest {

    @Test
    void contextLoads() {

    }

    @Test
    void sanityCheck() {
        Object target = new Object();
        Class<?> targetClass = AbusedClass.class;
        Injector sampleInjector = new Injector();
        AnnotatedFieldInjector injector = new AnnotatedFieldInjector();

        assertThrows(IllegalArgumentException.class, () -> injector.inject(null, target, sampleInjector));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(targetClass, null, sampleInjector));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(targetClass, target, null));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(null, null, null));
    }

    @Test
    void injectionCheck() {
        Container container = new Container();
        Injector injector = new Injector();

        container.registerType(AbusedClass.class);
        container.registerType(AbusedChildClass.class);

        AbusedClass abusedClass = injector.get(AbusedClass.class);

        assertNotNull(abusedClass);
        assertNotNull(abusedClass.abusedClass);
        assertNull(abusedClass.abusedClassNotWired);
    }

}
