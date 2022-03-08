package me.dartasen.dependencyinjector.utils;

import lombok.extern.slf4j.Slf4j;

import javax.management.ReflectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReflectionHelper {

    private static Object instanceFor(Class<?> clazz) {
        Object instance;

        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            log.error("Error while creating an instance");
            throw new RuntimeException(String.format("Create instance failed for class %s, error: %s", clazz.getCanonicalName(), ex.getMessage()));
        }
        return instance;
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return (T) instanceFor(clazz);
        } catch (Exception ex) {
            log.error("Unable to cast instance");
            throw new RuntimeException(String.format("Cast instance failed for class %s, error: %s", clazz.getCanonicalName(), ex.getMessage()));
        }
    }

    public static List<Field> getAnnotatedField(Class<?> clazz, final Class<? extends Annotation> annotation) {
        Collection<Field> allFields = Arrays.asList(clazz.getDeclaredFields());

        return allFields.stream()
                .filter(field -> field.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

}
