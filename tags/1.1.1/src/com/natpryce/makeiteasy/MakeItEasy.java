package com.natpryce.makeiteasy;

import java.util.*;


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
    
    public static <T> List<T> listOf(Maker<? extends T> ... makers) {
        return fill(new ArrayList<T>(makers.length), makers);
    }
    
    public static <T> Set<T> setOf(Maker<? extends T> ... makers) {
        return fill(new HashSet<T>(), makers);
    }
    
    public static <T extends Comparable<T>> SortedSet<T> sortedSetOf(Maker<? extends T> ... makers) {
        return fill(new TreeSet<T>(), makers);
    }
    
    private static <T, C extends Collection<T>> C fill(C collection, Maker<? extends T>... makers) {
        for (Maker<? extends T> maker : makers) {
            collection.add(maker.make());
        }
        return collection;
    }
}
