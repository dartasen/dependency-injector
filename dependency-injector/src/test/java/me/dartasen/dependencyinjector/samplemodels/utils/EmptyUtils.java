package me.dartasen.dependencyinjector.samplemodels.utils;

import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.samplemodels.interfaces.IEmptyInterface;

import java.io.Serializable;

@Component
public class EmptyUtils implements IEmptyInterface, Serializable {
}
