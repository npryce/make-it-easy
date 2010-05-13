package com.natpryce.makeiteasy;

/**
 * The value of a property.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
public class PropertyValue<T,V> {
    private final V value;
    private final Property<T,V> property;
    
    public PropertyValue(Property<T,V> property, V value) {
        this.property = property;
        this.value = value;
    }

    /**
     * The property's value
     */
    public V value() {
        return value;
    }

    /**
     * The property
     */
    public Property<T, V> property() {
        return property;
    }
}
