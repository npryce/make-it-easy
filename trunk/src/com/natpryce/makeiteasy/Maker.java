package com.natpryce.makeiteasy;

import java.util.HashMap;
import java.util.Map;


public class Maker<T> implements PropertyLookup<T>, PropertyProvider<T> {
    private final Instantiator<T> instantiator;
    private final Map<Property<? super T, ?>, Object> values = new HashMap<Property<? super T, ?>, Object>();

    public Maker(Instantiator<T> instantiator, PropertyProvider<? super T>... propertyProviders) {
        this.instantiator = instantiator;
        for (PropertyProvider<? super T> provider : propertyProviders) {
            provider.providePropertiesTo(new PropertyCollector<T>() {
                @Override
                public <V> void collectPropertyValue(Property<? super T, V> property, V value) {
                    values.put(property, value);
                }
            });
        }
    }


    public T make() {
        return instantiator.instantiate(this);
    }

    @Override
    @SuppressWarnings({"SuspiciousMethodCalls"})
    public <V> V valueOf(Property<? super T, V> property, V defaultValue) {
        if (values.containsKey(property)) {
            return (V)values.get(property);
        }
        else {
            return defaultValue;
        }
    }

    @Override
    public void providePropertiesTo(PropertyCollector<? extends T> propertyCollector) {
        for (Property<? super T, ?> property : values.keySet()) {
            Object value = values.get(property);

            // Cast necessary to work around weakness in wildcards
            propertyCollector.collectPropertyValue((Property<? super T,Object>) property, value);
        }
    }
}
