package me.dartasen.dependencyinjector.test;

import me.dartasen.dependencyinjector.Container;
import me.dartasen.dependencyinjector.models.RegisterOptions;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.models.exceptions.InvalidBeanException;
import me.dartasen.dependencyinjector.models.exceptions.InvalidHierarchyException;
import me.dartasen.dependencyinjector.test.samplemodels.abstracts.AbstractEmptyClass;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.IEmptyInterface;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.IExtendedEmptyInterface;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.ITestService;
import me.dartasen.dependencyinjector.test.samplemodels.services.TestService;
import me.dartasen.dependencyinjector.test.samplemodels.services.TestServiceWithAbstract;
import me.dartasen.dependencyinjector.test.samplemodels.utils.EmptyUtils;
import me.dartasen.dependencyinjector.test.samplemodels.utils.ExtendedUtils;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    void contextLoads() {

    }

    @Test
    void registerSanityCheck() {
        Container container = new Container();

        assertEquals(0, container.getDependencyMap().size());
        assertEquals(0, container.getSingletonCache().size());

        // check for null arguments
        assertThrows(IllegalArgumentException.class, () -> container.registerType(null));
        assertThrows(IllegalArgumentException.class, () -> container.registerType(null, Object.class));
        assertThrows(IllegalArgumentException.class, () -> container.registerType(Object.class, (Class<?>) null));
        assertThrows(IllegalArgumentException.class, () -> container.registerType(Object.class, EnumSet.noneOf(RegisterOptions.class)));
        assertThrows(IllegalArgumentException.class, () -> container.registerType(Object.class, (EnumSet<RegisterOptions>) null));

        // base class and sub class are not affiliated in any way
        assertThrows(InvalidHierarchyException.class, () -> container.registerType(AbstractEmptyClass.class, EmptyUtils.class));
        assertThrows(InvalidHierarchyException.class, () -> container.registerType(IExtendedEmptyInterface.class, EmptyUtils.class));

        // sub class cannot be an abstract type
        assertThrows(InvalidBeanException.class, () -> container.registerType(IEmptyInterface.class));
        assertThrows(InvalidBeanException.class, () -> container.registerType(AbstractEmptyClass.class));

        assertDoesNotThrow(() -> container.registerType(TestService.class));
        assertDoesNotThrow(() -> container.registerType(AbstractEmptyClass.class, TestServiceWithAbstract.class));
        assertDoesNotThrow(() -> container.registerType(EmptyUtils.class));
        assertDoesNotThrow(() -> container.registerType(ExtendedUtils.class));
    }

    @Test
    void testRegisterWithSpecificOptions() {
        Container container = new Container();

        assertEquals(0, container.getDependencyMap().size());
        assertEquals(0, container.getSingletonCache().size());

        container.registerType(TestService.class, EnumSet.of(RegisterOptions.AS_SELF, RegisterOptions.AS_IMPLEMENTED_INTERFACES));
        assertEquals(TestService.class.getInterfaces()[0], ITestService.class);

        assertNotNull(container.getDependencyMap().get(ITestService.class));
        assertNotNull(container.getDependencyMap().get(TestService.class));

        container.getDependencyMap().clear();

        container.registerType(TestService.class, EnumSet.of(RegisterOptions.AS_SELF));
        assertNotNull(container.getDependencyMap().get(TestService.class));
        assertNull(container.getDependencyMap().get(ITestService.class));

        container.registerType(TestServiceWithAbstract.class, EnumSet.of(RegisterOptions.AS_EXTENDED_TYPE));
        assertNotNull(container.getDependencyMap().get(AbstractEmptyClass.class));
        assertNull(container.getDependencyMap().get(TestServiceWithAbstract.class));

        // Make sure weird dependency doesn't get added somehow
        assertNull(container.getDependencyMap().get(Object.class));
        assertNull(container.getDependencyMap().get(Serializable.class));
        assertNull(container.getDependencyMap().get(Cloneable.class));
        assertNull(container.getDependencyMap().get(EventListener.class));
        assertNull(container.getDependencyMap().get(Remote.class));
    }

    @Test
    void testScan() {
        final String PACKAGE_NAME = EmptyUtils.class.getPackage().getName();
        Container container = new Container();

        Reflections reflections = new Reflections(PACKAGE_NAME, Scanners.values());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Component.class);

        container.scan(PACKAGE_NAME);

        for (Class<?> clazz : classes) {
            assertNotNull(container.getDependencyMap().get(clazz));
        }
    }
}
