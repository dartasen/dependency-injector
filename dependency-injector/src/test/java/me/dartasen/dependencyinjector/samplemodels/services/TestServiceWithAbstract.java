package me.dartasen.dependencyinjector.samplemodels.services;

import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.samplemodels.abstracts.AbstractEmptyClass;
import me.dartasen.dependencyinjector.samplemodels.utils.EmptyUtils;

@Component
public class TestServiceWithAbstract extends AbstractEmptyClass {

    @Autowired
    public EmptyUtils emptyUtils;

}
