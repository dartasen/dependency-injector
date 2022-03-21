package me.dartasen.dependencyinjector;

import lombok.extern.slf4j.Slf4j;
import me.dartasen.dependencyinjector.models.IInjector;
import me.dartasen.dependencyinjector.injectors.AbstractDependencyInjector;
import me.dartasen.dependencyinjector.injectors.AnnotatedFieldInjector;
import me.dartasen.dependencyinjector.injectors.AnnotatedSetterInjector;
import me.dartasen.dependencyinjector.models.LifeCycle;
import me.dartasen.dependencyinjector.models.annotations.Component;
import me.dartasen.dependencyinjector.models.exceptions.BeanCreationException;

@Slf4j
public final class Injector implements IInjector {

    private static final AbstractDependencyInjector[] dependencyInjectors = new AbstractDependencyInjector[]{
            new AnnotatedFieldInjector(),
            new AnnotatedSetterInjector()
    };

    private final Container container;

    public Injector() {
        this.container = new Container();
    }

    public Injector(Container container) {
        if (container == null) throw new IllegalArgumentException("Container cannot be null");
        this.container = container;
    }

    public <T> T get(Class<T> type) {
        return get(type, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type, LifeCycle lifeCycle) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");

        Class<?> contract = container.getClassForContract(type);
        if (contract == null) return null;

        if (lifeCycle == null) {
            lifeCycle = contract.getAnnotation(Component.class).lifecycle();
        }

        T instance = switch (lifeCycle) {
            case SINGLETON -> {
                T object = (T) container.getSingletonCache().get(contract);
                // If we don't have any singleton available
                if (object == null) {
                    object = (T) newInstance(contract);
                    container.getSingletonCache().put(contract, object);
                }
                yield object;
            }

            case INSTANCE -> (T) newInstance(contract);

            default -> throw new BeanCreationException(String.format("Unsupported lifecycle : %s", lifeCycle.name()));
        };

        for (AbstractDependencyInjector dependencyInjector : dependencyInjectors) {
            dependencyInjector.inject(contract, instance, this);
        }

        return instance;
    }

    private <T> T newInstance(Class<T> type) {
        try {
            return (T) type.getConstructor().newInstance();
        } catch (NoSuchMethodException ex) {
            throw new BeanCreationException(String.format("Unable to find any constructor of %s (maybe the default constructor is private or does not exist at all ?)", type.getName()));
        } catch (Exception ex) {
            throw new BeanCreationException(ex.getMessage());
        }
    }
}
