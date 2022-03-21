package me.dartasen.dependencyinjector.test.samplemodels.services;

import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.test.samplemodels.abstracts.AbstractEmptyClass;
import me.dartasen.dependencyinjector.test.samplemodels.utils.EmptyUtils;

@Component
public class TestServiceWithAbstract extends AbstractEmptyClass {

    @Autowired
    public EmptyUtils emptyUtils;

}
