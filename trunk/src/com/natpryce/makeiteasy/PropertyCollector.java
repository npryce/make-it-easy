package com.natpryce.makeiteasy;

public interface PropertyCollector<T> {
    <V> void collectPropertyValue(Property<? super T, V> property, V value);
}
