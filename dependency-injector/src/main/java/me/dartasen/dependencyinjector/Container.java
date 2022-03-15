package me.dartasen.dependencyinjector;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.dartasen.dependencyinjector.models.IContainer;
import me.dartasen.dependencyinjector.models.RegisterOptions;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.models.exceptions.InvalidBeanException;
import me.dartasen.dependencyinjector.models.exceptions.InvalidHierarchyException;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.rmi.Remote;
import java.util.*;

@Slf4j
public class Container implements IContainer {

    // base class => implementation class
    @Getter
    private final Map<Class<?>, Class<?>> dependencyMap = new HashMap<>();

    @Getter
    private final Map<Class<?>, Object> singletonCache = new HashMap<>();

    public void registerType(Class<?> type) {
        registerType(type, type);
    }

    public void registerType(Class<?> type, EnumSet<RegisterOptions> options) {

        if (options == null || options.isEmpty()) throw new IllegalArgumentException("Register options are invalid");

        if (options.contains(RegisterOptions.AS_SELF)) {
            registerType(type, type);
        }

        if (options.contains(RegisterOptions.AS_EXTENDED_TYPE)) {
            Class<?> superClass = type.getSuperclass();
            if (superClass != null) {
                registerType(superClass, type);
            }
        }

        if (options.contains(RegisterOptions.AS_FIRST_INTERFACE)) {
            Class<?>[] interfaces = type.getInterfaces();

            if (interfaces.length > 0) {
                registerType(interfaces[0], type);
            }
        }

        if (options.contains(RegisterOptions.AS_IMPLEMENTED_INTERFACES)) {
            Class<?>[] interfaces = type.getInterfaces();

            for (Class<?> interfacez : interfaces) {
                registerType(interfacez, type);
            }
        }
    }

    public void registerType(Class<?> baseClass, Class<?> subClass) {
        if (baseClass == null || subClass == null) throw new IllegalArgumentException("Unable to find null type");
        if (!baseClass.isAssignableFrom(subClass)) throw new InvalidHierarchyException("Subclass is not assignable from baseclass");
        if (!subClass.isAnnotationPresent(Component.class)) throw new InvalidBeanException("Components annotations are missing");

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
        if (mainClass == null) throw new IllegalArgumentException("MainClass cannot be null");

        String packageName = mainClass.getPackage().getName();
        scan(packageName);
    }

    public void scan(String packageName) {
        if (packageName == null || packageName.trim().equals("")) {
            throw new IllegalArgumentException("Invalid package specified for scan");
        }

        log.info("Starting to scan {}", packageName);

        Reflections reflections = new Reflections(packageName, Scanners.values());
        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : clazzes) {
            Class<?>[] interfaces = clazz.getInterfaces();

            for (Class<?> clazzInterface : interfaces) {
                registerType(clazzInterface, clazz);
            }

            registerType(clazz);
        }
    }

}
