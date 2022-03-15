package me.dartasen.dependencyinjector.injectors;

import me.dartasen.dependencyinjector.Injector;
import me.dartasen.dependencyinjector.models.annotations.Autowired;
import me.dartasen.dependencyinjector.models.exceptions.BeanCreationException;

import java.lang.reflect.Method;

public class AnnotatedSetterInjector extends AbstractDependencyInjector {

    @Override
    public void inject(Class<?> type, Object target, Injector injector) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        if (target == null) throw new IllegalArgumentException("Target cannot be null");
        if (injector == null) throw new IllegalArgumentException("Injector cannot be null");

        for (Method method : type.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                Class<?> requiredType = getInjectableType(method);
                setValue(target, method, injector.get(requiredType));
            }
        }
    }

    private void setValue(Object target, Method method, Object value) {
        boolean currentState = method.canAccess(target);

        try {
            if (!currentState) {
                method.setAccessible(true);
            }

            method.invoke(target, value);
            method.setAccessible(currentState);
        } catch (Exception ex) {
            throw new BeanCreationException(String.format("Unable to create bean : %s", ex.getMessage()));
        }
    }

    private Class<?> getInjectableType(Method method) {
        if (method.getParameterCount() != 1) {
            throw new BeanCreationException(String.format("%s does not seem to be a correct setter (1 parameter)", method.getName()));
        }

        return method.getParameterTypes()[0];
    }
}
