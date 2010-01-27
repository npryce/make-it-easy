package com.natpryce.makeiteasy;

import java.util.HashMap;
import java.util.Map;


public class Maker<T> implements PropertyLookup<T> {
    private final Instantiator<T> instantiator;
    private final Map<Property<? super T, ?>, Object> values;

    public Maker(Instantiator<T> instantiator, PropertyValue<? super T, ?>... propertyValues) {
        this.instantiator = instantiator;
        this.values = new HashMap<Property<? super T, ?>, Object>();
        setPropertyValues(propertyValues);
    }

    private Maker(Maker<T> that, PropertyValue<? super T, ?>... propertyValues) {
        this.instantiator = that.instantiator;
        this.values = new HashMap<Property<? super T,?>, Object>(that.values);
        setPropertyValues(propertyValues);
    }

    private void setPropertyValues(PropertyValue<? super T, ?>[] propertyValues) {
        for (PropertyValue<? super T, ?> propertyValue : propertyValues) {
            values.put(propertyValue.property, propertyValue.value);
        }
    }

    public T make() {
        return instantiator.instantiate(this);
    }

    public Maker<T> but(PropertyValue<? super T, ?>... propertyValues) {
        return new Maker<T>(this, propertyValues);
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
}
