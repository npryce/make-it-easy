package com.natpryce.makeiteasy;

public interface PropertyValue<T, V> {
    V value();

    Property<T, V> property();
}
