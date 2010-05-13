package com.natpryce.makeiteasy;

/**
 * The value of a property that is shared among all instances created by a Maker.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
public class SharedPropertyValue<T,V> implements PropertyValue<T,V> {
    private final Property<T,V> property;
    private final V value;

    public SharedPropertyValue(Property<T,V> property, V value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public Property<T, V> property() {
        return property;
    }
}
