package com.natpryce.makeiteasy;

/**
 * The value of a property.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
public class PropertyValue<T, V> {
    private final Property<T,V> property;
    private final Donor<? extends V> valueDonor;

    public PropertyValue(Property<T, V> property, Donor<? extends V> valueDonor) {
        this.property = property;
        this.valueDonor = valueDonor;
    }

    /**
     * The property
     */
    public Property<T, V> property() {
        return property;
    }

    /**
     * The property's value
     */
    public V value() {
        return valueDonor.value();
    }
}
