package com.natpryce.makeiteasy;

public interface PropertyCollector<T> {
    void collectPropertyValue(PropertyValue<? super T, ?> propertyValue);
}
