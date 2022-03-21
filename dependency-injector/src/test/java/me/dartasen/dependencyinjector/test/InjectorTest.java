package me.dartasen.dependencyinjector.test;

import me.dartasen.dependencyinjector.Container;
import me.dartasen.dependencyinjector.Injector;
import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.RegisterOptions;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.ITestService;
import me.dartasen.dependencyinjector.test.samplemodels.services.TestService;
import me.dartasen.dependencyinjector.test.samplemodels.utils.EmptyUtils;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {

    @Test
    void contextLoads() {

    }

    @Test
    void sanityCheck() {
        Injector injector = new Injector();

        assertThrows(IllegalArgumentException.class, () -> new Injector(null));
        assertThrows(IllegalArgumentException.class, () -> injector.get(null));
        assertThrows(IllegalArgumentException.class, () -> injector.get(null, null));
        assertThrows(Exception.class, () -> injector.get(null, LifeCycle.UNKNOWN));
    }

    @Test
    void getTest() {
        Container container = new Container();
        Injector injector = new Injector(container);

        assertNull(injector.get(TestService.class));
        assertNull(injector.get(EmptyUtils.class));

        container.registerType(TestService.class, EnumSet.of(RegisterOptions.AS_SELF, RegisterOptions.AS_IMPLEMENTED_INTERFACES));
        container.registerType(EmptyUtils.class, EnumSet.of(RegisterOptions.AS_SELF, RegisterOptions.AS_IMPLEMENTED_INTERFACES));

        EmptyUtils test = injector.get(EmptyUtils.class);
        assertNotNull(test);

        TestService testService = injector.get(TestService.class);
        assertNotNull(testService);

        ITestService iTestService = injector.get(ITestService.class);
        assertNotNull(iTestService);

        assertEquals(testService, iTestService);
    }

    @Test
    void lifeCycleTest() {

    }
}
