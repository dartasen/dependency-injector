package me.dartasen.dependencyinjector.models;

import java.util.EnumSet;

public interface IContainer {

    void registerType(Class<?> type);

    void registerType(Class<?> type, EnumSet<RegisterOptions> options);

    void registerType(Class<?> baseClass, Class<?> subClass);

    Class<?> getClassForContract(Class<?> contract);

    void scan(String packageName);

}
