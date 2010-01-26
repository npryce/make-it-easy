package com.natpryce.makeiteasy;

import java.util.HashMap;
import java.util.Map;


public class Maker<T> implements PropertyLookup<T> {
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

    @SuppressWarnings({"SuspiciousMethodCalls"})
    public <V> V valueOf(Property<? super T, V> property, V defaultValue) {
        if (values.containsKey(property)) {
            return (V)values.get(property);
        }
        else {
            return defaultValue;
        }
    }

    public static <T> Maker<T> a(Instantiator<T> instantiator, PropertyProvider<? super T> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }
    
    public static <T> Maker<T> an(Instantiator<T> instantiator, PropertyProvider<? super T> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }

    public static <T> T make(Maker<T> maker) {
        return maker.make();
    }

    public static <T> PropertyProvider<T> like(final Maker<T> maker) {
        return new PropertyProvider<T>() {
            @Override
            public void providePropertiesTo(PropertyCollector<? extends T> propertyCollector) {
                for (Property<? super T, ?> property : maker.values.keySet()) {
                    Object value = maker.values.get(property);

                    // Cast necessary to work around weakness in wildcards
                    propertyCollector.collectPropertyValue((Property<? super T,Object>) property, value);
                }
            }
        };
    }
    
    public static <T,V> PropertyValue<T,V> with(Property<T,V> property, V value) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T,V> PropertyValue<T,V> with(V value, Property<T,V> property) {
        return new PropertyValue<T,V>(property, value);
    }
}
