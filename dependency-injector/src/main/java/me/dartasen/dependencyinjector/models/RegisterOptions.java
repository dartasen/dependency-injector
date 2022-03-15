package me.dartasen.dependencyinjector.models;

import java.util.EnumSet;

/**
 * Used to specify how a given type will be registered in Container's dependency map
 */
public enum RegisterOptions {

    AS_SELF,
    AS_EXTENDED_TYPE,
    AS_FIRST_INTERFACE,
    AS_IMPLEMENTED_INTERFACES;

    public static final EnumSet<RegisterOptions> DEFAULT = EnumSet.of(AS_SELF, AS_EXTENDED_TYPE, AS_FIRST_INTERFACE);
    public static final EnumSet<RegisterOptions> ALL = EnumSet.of(AS_SELF, AS_EXTENDED_TYPE, AS_IMPLEMENTED_INTERFACES);
}
