package me.dartasen.dependencyinjector.models;

public interface IContainer {

    void register(Class<?> baseClass, Class<?> subClass);

    Class<?> getClassForContract(Class<?> contract);

    void scan(String packageName);

}
