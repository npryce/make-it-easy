package com.natpryce.makeiteasy;


import org.pcollections.HashTreePMap;
import org.pcollections.PMap;


/**
 * Makes objects of a given type with a specified initial state.
 *
 * @param <T> the type of object to make
 */
public class Maker<T> implements PropertyLookup<T>, Donor<T> {
    private final PMap<Property<? super T, ?>, PropertyValue<? super T, ?>> values;
    private final Instantiator<T> instantiator;

    /**
     * Creates a Maker for objects of a given type with a given initial state.
     *
     * @param instantiator   creates the new objects
     * @param propertyValues define the initial state of the new objects
     */
    @SafeVarargs
    public Maker(Instantiator<T> instantiator, PropertyValue<? super T, ?>... propertyValues) {
        this.instantiator = instantiator;
        this.values = byProperty(propertyValues);
    }

    @SafeVarargs
    private Maker(Maker<T> that, PropertyValue<? super T, ?>... propertyValues) {
        this.instantiator = that.instantiator;
        this.values = that.values.plusAll(byProperty(propertyValues));
    }

    private static <T> PMap<Property<? super T, ?>, PropertyValue<? super T, ?>> byProperty(PropertyValue<? super T, ?>[] propertyValues) {
        PMap<Property<? super T, ?>, PropertyValue<? super T, ?>> propertyMap = HashTreePMap.empty();

        for (PropertyValue<? super T, ?> propertyValue : propertyValues) {
            propertyMap = propertyMap.plus(propertyValue.property(), propertyValue);
        }

        return propertyMap;
    }

    /**
     * Makes a new object.
     * <p>
     * The {@link com.natpryce.makeiteasy.MakeItEasy#make(Maker) MakeItEasy.make} method
     * is syntactic sugar to make calls to this method read more naturally in most
     * contexts.
     *
     * @return a new object
     */
    public T make() {
        return instantiator.instantiate(this);
    }

    @Override
    public T value() {
        return make();
    }

    /**
     * Returns a new Maker for the same type of object and with the same initial state
     * except where overridden by the given <var>propertyValues</var>.
     *
     * @param propertyValues those initial properties of the new Make that will differ from this Maker
     * @return a new Maker
     */
    @SafeVarargs
    public final Maker<T> but(PropertyValue<? super T, ?>... propertyValues) {
        return new Maker<>(this, propertyValues);
    }

    @Override
    public <V> V valueOf(Property<? super T, V> property, V defaultValue) {
        return valueOf(property, new SameValueDonor<>(defaultValue));
    }

    @Override
    public <V> V valueOf(Property<? super T, V> property, Donor<? extends V> defaultValue) {
        if (values.containsKey(property)) {
            //noinspection unchecked
            return (V) values.get(property).value();
        } else {
            return defaultValue.value();
        }
    }
}
