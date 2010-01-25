package com.natpryce.makeiteasy;

import com.natpryce.makeiteasy.PropertyValue;

public interface PropertyCollector<T> {
    void collectPropertyValue(PropertyValue<? super T, ?> propertyValue);
}
