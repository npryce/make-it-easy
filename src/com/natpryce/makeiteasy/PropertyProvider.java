package com.natpryce.makeiteasy;

import com.natpryce.makeiteasy.PropertyCollector;

public interface PropertyProvider<T> {
    void providePropertiesTo(PropertyCollector<? extends T> collector);
}
