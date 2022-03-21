package me.dartasen.dependencyinjector.test.samplemodels.utils;

import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.test.samplemodels.interfaces.IExtendedEmptyInterface;

@Component
public class ExtendedUtils extends EmptyUtils implements IExtendedEmptyInterface {

}
