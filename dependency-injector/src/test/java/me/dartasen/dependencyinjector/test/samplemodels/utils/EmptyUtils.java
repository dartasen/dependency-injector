package me.dartasen.dependencyinjector.test.samplemodels.utils;

import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.IEmptyInterface;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.EventListener;

@Component
public class EmptyUtils implements IEmptyInterface, Serializable, Cloneable, Remote, EventListener {

    @Override
    public EmptyUtils clone() {
        try {
            return (EmptyUtils) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
