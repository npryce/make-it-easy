package com.natpryce.makeiteasy;

/**
 * The value of a property.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
public interface PropertyValue<T, V> {
    /**
     * The property
     */
    Property<T, V> property();

    /**
     * The property's value
     */
    V value();
}
