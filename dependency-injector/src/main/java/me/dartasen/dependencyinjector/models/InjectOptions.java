package me.dartasen.dependencyinjector.models;

import java.util.EnumSet;

/**
 * Used to specify which workload the injector should do to build a given type
 */
public enum InjectOptions {
    NONE,
    FIELD,
    SETTER,
    CONSTRUCTOR;

    public static final EnumSet<InjectOptions> DEFAULT = EnumSet.of(FIELD, CONSTRUCTOR);
    public static final EnumSet<InjectOptions> ALL = EnumSet.of(FIELD, SETTER, CONSTRUCTOR);
}
