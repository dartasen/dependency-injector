package me.dartasen.dependencyinjector.models.annotations;

import me.dartasen.dependencyinjector.models.LifeCycle;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify to the framework that this component should be managed by the framework
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    LifeCycle lifecycle() default LifeCycle.SINGLETON;
}
