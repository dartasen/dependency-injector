package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.Injector;
import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.exceptions.BeanCreationException;

import java.lang.reflect.Field;

public class AnnotatedFieldInjector extends AbstractDependencyInjector {

    @Override
    public void inject(Class<?> type, Object target, Injector injector) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        if (target == null) throw new IllegalArgumentException("Target cannot be null");
        if (injector == null) throw new IllegalArgumentException("Injector cannot be null");

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                setPropertyValue(target, field, injector.get(field.getType()));
            }
        }
    }

    private void setPropertyValue(Object target, Field field, Object value) {
        boolean currentState = field.canAccess(target);

        try {
            if (!currentState) {
                field.setAccessible(true);
            }

            field.set(target, value);
            field.setAccessible(currentState);
        } catch (Exception ex) {
            throw new BeanCreationException(String.format("Unable to create bean : %s", ex.getMessage()));
        }
    }

}
