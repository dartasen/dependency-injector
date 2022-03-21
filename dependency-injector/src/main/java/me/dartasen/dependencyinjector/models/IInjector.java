package me.dartasen.dependencyinjector.models;

public interface IInjector {

    <T> T get(Class<T> type);

    <T> T get(Class<T> type, LifeCycle lifeCycle);

}
