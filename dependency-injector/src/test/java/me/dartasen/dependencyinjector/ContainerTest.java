package me.dartasen.dependencyinjector;

import me.dartasen.dependencyinjector.models.RegisterOptions;
import me.dartasen.dependencyinjector.models.exceptions.InvalidBeanException;
import me.dartasen.dependencyinjector.models.exceptions.InvalidHierarchyException;
import me.dartasen.dependencyinjector.samplemodels.abstracts.AbstractEmptyClass;
import me.dartasen.dependencyinjector.samplemodels.interfaces.IEmptyInterface;
import me.dartasen.dependencyinjector.samplemodels.interfaces.IExtendedEmptyInterface;
import me.dartasen.dependencyinjector.samplemodels.services.TestService;
import me.dartasen.dependencyinjector.samplemodels.services.TestServiceWithAbstract;
import me.dartasen.dependencyinjector.samplemodels.utils.EmptyUtils;
import me.dartasen.dependencyinjector.samplemodels.utils.ExtendedUtils;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

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
    }
}
