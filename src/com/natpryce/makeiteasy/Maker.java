package com.natpryce.makeiteasy;

import java.util.HashMap;
import java.util.Map;


/**
 * Makes objects of a given type with a specified initial state.
 *
 * @param <T> the type of object to make
 */
public class Maker<T> implements PropertyLookup<T> {
    private final Instantiator<T> instantiator;
    private final Map<Property<? super T, ?>, Object> values;

    /**
     * Creates a Maker for objects of a given type with a given initial state.
     * 
     * @param instantiator creates the new objects
     * @param propertyValues define the initial state of the new objects
     */
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
            values.put(propertyValue.property(), propertyValue.value());
        }
    }

    /**
     * Makes a new object.
     *
     * The {@link com.natpryce.makeiteasy.MakeItEasy#make(Maker) MakeItEasy.make} method
     * is syntactic sugar to make calls to this method read more naturally in most
     * contexts.
     *
     * @return a new object
     */
    public T make() {
        return instantiator.instantiate(this);
    }

    /**
     * Returns a new Maker for the same type of object and with the same initial state
     * except where overridden by the given <var>propertyValues</var>.
     *
     * @param propertyValues those initial properties of the new Make that will differ from this Maker
     * @return a new Maker
     */
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
