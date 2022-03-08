package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.exceptions.BeanCreationException;

import java.lang.reflect.Field;

public class AnnotatedFieldInjector extends AbstractDependencyInjector {

    public AnnotatedFieldInjector(Class<?> type) {
        super(type);
    }

    @Override
    public void inject(Object target) {
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                // TODO : Get inject type
                setPropertyValue(target, field, null);
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
