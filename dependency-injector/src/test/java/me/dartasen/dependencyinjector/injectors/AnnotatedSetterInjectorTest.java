package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.Container;
import me.dartasen.dependencyinjector.Injector;
import me.dartasen.dependencyinjector.injectors.models.AbusedChildChildClass;
import me.dartasen.dependencyinjector.injectors.models.AbusedChildClass;
import me.dartasen.dependencyinjector.injectors.models.AbusedClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotatedSetterInjectorTest {

    @Test
    void contextLoads() {

    }

    @Test
    void sanityCheck() {
        Object target = new Object();
        Class<?> targetClass = AbusedClass.class;
        Injector sampleInjector = new Injector();
        AnnotatedSetterInjector injector = new AnnotatedSetterInjector();

        assertThrows(IllegalArgumentException.class, () -> injector.inject(null, target, sampleInjector));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(targetClass, null, sampleInjector));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(targetClass, target, null));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(null, null, null));
    }

    @Test
    void injectionCheck() {
        Container container = new Container();
        Injector injector = new Injector(container);

        container.registerType(AbusedClass.class);
        container.registerType(AbusedChildClass.class);
        container.registerType(AbusedChildChildClass.class);

        AbusedChildChildClass abusedChildChildClass = injector.get(AbusedChildChildClass.class);
        assertNotNull(abusedChildChildClass);

        AbusedClass abusedClass = injector.get(AbusedClass.class);
        assertNotNull(abusedClass);

        AbusedChildClass abusedChildClass = injector.get(AbusedChildClass.class);
        assertNotNull(abusedChildClass);

        // Since our default lifecycle mode is SINGLETON, those should be equal
        assertEquals(abusedChildChildClass.abusedClass, abusedClass);
        assertEquals(abusedClass.abusedClass, abusedChildClass);
    }

}
