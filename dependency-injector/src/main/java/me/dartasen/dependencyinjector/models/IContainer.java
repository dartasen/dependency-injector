package me.dartasen.dependencyinjector.models;

import java.util.EnumSet;

public interface IContainer {

    /**
     * Register a type as it self implementation in the dependency map
     * @param type type to register
     */
    void registerType(Class<?> type);

    /**
     * Register a type in the dependency map using custom options
     * @param type Type to register
     * @param options Options needed for a register (register superclass, interfaces, ...)
     */
    void registerType(Class<?> type, EnumSet<RegisterOptions> options);

    /**
     * Register a dependency link in the container dependency map
     * @param baseClass Abstract, Interface, superclass or self implementation
     * @param subClass Implementation class of baseClass (cannot be an abstract type)
     */
    void registerType(Class<?> baseClass, Class<?> subClass);

    /**
     * Fetch the dependency map to find the corresponding implementation
     * @param contract Abstract, Interface, superclass or self implementation
     */
    Class<?> getClassForContract(Class<?> contract);

    /**
     * Recursively scan annotated class (with @see me.dartasen.dependencyinjector.models.annotations.Component) within the package of a given class
     * @param rootClass main class of the program
     */
    void scan(Class<?> rootClass);

    /**
     * Recursively scan annotated class (with @see me.dartasen.dependencyinjector.models.annotations.Component) within a given package
     * @param packageName package to scan
     */
    void scan(String packageName);

}
