package me.dartasen.dependencyinjector;

import lombok.extern.slf4j.Slf4j;
import me.dartasen.dependencyinjector.models.IContainer;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.models.exceptions.InvalidHierarchyException;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class Container implements IContainer {

    // base class => implementation class
    private final Map<Class<?>, Class<?>> dependencyMap = new HashMap<>();

    public void register(Class<?> baseClass, Class<?> subClass) {
        if (baseClass == null || subClass == null) {
            throw new IllegalArgumentException("Unable to find null type");
        }

        if (!subClass.isAssignableFrom(baseClass)) {
            throw new InvalidHierarchyException("Subclass is not assignable from baseclass");
        }

        // If we don't want to allow abstract types as implementation
        if (Modifier.isAbstract(subClass.getModifiers()) || subClass.isInterface()) {
            throw new InvalidHierarchyException("Subclass type cannot be abstract to register a mapping");
        }

        dependencyMap.put(baseClass, subClass.asSubclass(baseClass));
    }

    public Class<?> getClassForContract(Class<?> contract) {
        return dependencyMap.getOrDefault(contract, contract.isInterface() ? null : contract);
    }

    public void scan(Class<?> mainClass) {
        if (mainClass == null) {
            throw new IllegalArgumentException("Invalid main class");
        }

        scan(mainClass.getPackageName());
    }

    public void scan(String packageName) {
        if (packageName == null || packageName.trim().equals("")) {
            throw new IllegalArgumentException("Invalid package specified for scan");
        }

        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : clazzes) {
            Class<?>[] interfaces = clazz.getInterfaces();

            for (Class<?> clazzInterface : interfaces) {
                dependencyMap.putIfAbsent(clazzInterface, clazz);
            }
        }
    }

}
