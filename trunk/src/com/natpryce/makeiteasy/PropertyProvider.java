package com.natpryce.makeiteasy;

public interface PropertyProvider<T> {
    void providePropertiesTo(PropertyCollector<? extends T> collector);
}
