package me.dartasen.dependencyinjector.samplemodels.services;

import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.samplemodels.interfaces.ITestService;
import me.dartasen.dependencyinjector.samplemodels.utils.EmptyUtils;
import me.dartasen.dependencyinjector.samplemodels.utils.ExtendedUtils;

@Component
public class TestService implements ITestService {

    @Autowired
    public EmptyUtils emptyUtils;

    @Autowired
    public ExtendedUtils extendedUtils;

}
