package com.natpryce.makeiteasy;

import java.util.HashMap;
import java.util.Map;


public abstract class Maker<T> implements PropertyProvider<T>, PropertyCollector<T> {
    private Map<Property<? super T, ?>, Object> values = new HashMap<Property<? super T, ?>, Object>();

    public Maker(PropertyProvider<? super T>... propertyProviders) {
        for (PropertyProvider<? super T> provider : propertyProviders) {
            provider.providePropertiesTo(this);
        }
    }
    
    public void collectPropertyValue(PropertyValue<? super T, ?> propertyValue) {
        values.put(propertyValue.property, propertyValue.value);
    }

    public void providePropertiesTo(PropertyCollector<? extends T> propertyCollector) {
        for (Map.Entry<Property<? super T, ?>, Object> entry : values.entrySet()) {
            values.put(entry.getKey(), entry.getValue());
        }
    }

    public abstract T make();

    @SuppressWarnings({"SuspiciousMethodCalls"})
    protected <T, V> V valueFor(Property<? super T, V> property, V defaultValue) {
        if (values.containsKey(property)) {
            return (V)values.get(property);
        }
        else {
            return defaultValue;
        }
    }
    
    public static <T> T make(Maker<T> maker) {
        return maker.make();
    }

    public static <T> PropertyProvider<T> like(Maker<T> maker) {
        return maker;
    }

    public static <T,V> PropertyValue<T,V> with(Property<T,V> property, V value) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T,V> PropertyValue<T,V> with(V value, Property<T,V> property) {
        return new PropertyValue<T,V>(property, value);
    }
}
