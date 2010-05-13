package com.natpryce.makeiteasy;


/**
 * The value of a property for which a distinct value instance is created
 * for each instance created by a Maker.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
public class DistinctPropertyValue<T,V> implements PropertyValue<T,V> {
    private final Property<T,V> property;
    private final Maker<V> valueMaker;

    public DistinctPropertyValue(Property<T, V> property, Maker<V> valueMaker) {
        this.property = property;
        this.valueMaker = valueMaker;
    }

    @Override
    public Property<T, V> property() {
        return property;
    }

    @Override
    public V value() {
        return valueMaker.make();
    }
}
