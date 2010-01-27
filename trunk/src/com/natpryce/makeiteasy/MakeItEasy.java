package com.natpryce.makeiteasy;

/**
 * Syntactic sugar for using Make It Easy test-data builders.
 */
public class MakeItEasy {
    public static <T> Maker<T> a(Instantiator<T> instantiator, PropertyValue<? super T, ?> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }
    
    public static <T> Maker<T> an(Instantiator<T> instantiator, PropertyValue<? super T, ?> ... propertyProviders) {
        return new Maker<T>(instantiator, propertyProviders);
    }

    public static <T,V> PropertyValue<T,V> with(Property<T,V> property, V value) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T,V> PropertyValue<T,V> with(V value, Property<T,V> property) {
        return new PropertyValue<T,V>(property, value);
    }

    public static <T,V> PropertyValue<T,V> with(Property<T,V> property, Maker<V> valueMaker) {
        return new PropertyValue<T,V>(property, valueMaker.make());
    }

    public static <T,V> PropertyValue<T,V> with(Maker<V> valueMaker, Property<T,V> property) {
        return new PropertyValue<T,V>(property, valueMaker.make());
    }
    
    public static <T> T make(Maker<T> maker) {
        return maker.make();
    }
}
