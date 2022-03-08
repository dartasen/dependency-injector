package me.dartasen.dependencyinjector.samples;

import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.annotations.Component;

@Component
public class TestService implements ITestService {

    @Autowired
    public ITestClass testClass;

}
