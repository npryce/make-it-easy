package com.natpryce.makeiteasy;

public class PropertyValue<T,V> {
    public final Property<T,V> property;
    public final V value;
    
    public PropertyValue(Property<T,V> property, V value) {
        this.property = property;
        this.value = value;
    }
}
