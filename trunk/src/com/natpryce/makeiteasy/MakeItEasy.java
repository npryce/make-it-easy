package com.natpryce.makeiteasy;

/**
 * Syntactic sugar for using Make It Easy test-data builders.
 */
public class MakeItEasy {
    public static <T> Maker<T> a(Instantiator<T> instantiator, PropertyProvider<? super T> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }

    public static <T> Maker<T> an(Instantiator<T> instantiator, PropertyProvider<? super T> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }

    public static <T> PropertyProvider<T> like(final Maker<T> maker) {
        return maker;
    }

    public static <T,V> PropertyValue<T,V> with(Property<T,V> property, V value) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T,V> PropertyValue<T,V> with(V value, Property<T,V> property) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T> T make(Maker<T> maker) {
        return maker.make();
    }
}
