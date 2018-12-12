package com.natpryce.makeiteasy;

import com.natpryce.makeiteasy.sequence.ElementsSequence;

import java.util.*;

import static java.util.Arrays.asList;


/**
 * Syntactic sugar for using Make It Easy test-data builders.
 */
public class MakeItEasy {
    @SafeVarargs
    public static <T> Maker<T> a(Instantiator<T> instantiator, PropertyValue<? super T, ?>... propertyProviders) {
        return new Maker<>(instantiator, propertyProviders);
    }

    @SafeVarargs
    public static <T> Maker<T> an(Instantiator<T> instantiator, PropertyValue<? super T, ?>... propertyProviders) {
        return new Maker<>(instantiator, propertyProviders);
    }

    public static <T, V, W extends V> PropertyValue<T, V> with(Property<T, V> property, W value) {
        return new PropertyValue<>(property, new SameValueDonor<V>(value));
    }

    public static <T, V, W extends V> PropertyValue<T, V> with(W value, Property<T, V> property) {
        return new PropertyValue<>(property, new SameValueDonor<V>(value));
    }

    public static <T, V, W extends V> PropertyValue<T, V> with(Property<T, V> property, Donor<W> valueDonor) {
        return new PropertyValue<>(property, valueDonor);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static <T, V, W extends V> PropertyValue<T, V> with(Donor<W> valueDonor, Property<T, V> property) {
        return new PropertyValue<>(property, valueDonor);
    }

    public static <T, V> PropertyValue<T, V> withNull(Property<T, V> property) {
        return new PropertyValue<>(property, new SameValueDonor<>(null));
    }

    @SafeVarargs
    public static <T> Donor<T> theSame(Instantiator<T> instantiator, PropertyValue<? super T, ?>... propertyProviders) {
        return theSame(an(instantiator, propertyProviders));
    }

    public static <T> Donor<T> theSame(Donor<T> originalDonor) {
        return new SameValueDonor<>(originalDonor.value());
    }

    public static <T> T make(Maker<T> maker) {
        return maker.value();
    }

    @SafeVarargs
    public static <T> Donor<List<T>> listOf(Donor<? extends T>... donors) {
        return new NewCollectionDonor<List<T>, T>(donors) {
            protected List<T> newCollection() {
                return new ArrayList<>();
            }
        };
    }

    @SafeVarargs
    public static <T> Donor<Set<T>> setOf(Donor<? extends T>... donors) {
        return new NewCollectionDonor<Set<T>, T>(donors) {
            protected Set<T> newCollection() {
                return new HashSet<>();
            }
        };
    }

    @SafeVarargs @SuppressWarnings({"UnusedDeclaration"})
    public static <T extends Comparable<T>> Donor<SortedSet<T>> sortedSetOf(Donor<? extends T>... donors) {
        return new NewCollectionDonor<SortedSet<T>, T>(donors) {
            protected SortedSet<T> newCollection() {
                return new TreeSet<>();
            }
        };
    }

    public static <T> Donor<T> from(final Iterable<T> values) {
        return new ElementsSequence<>(values, Collections.emptyList());
    }

    @SafeVarargs
    public static <T> Donor<T> from(T... values) {
        return from(asList(values));
    }

    public static <T> Donor<T> fromRepeating(Iterable<T> values) {
        return new ElementsSequence<>(values, values);
    }

    @SafeVarargs
    public static <T> Donor<T> fromRepeating(T... values) {
        return fromRepeating(asList(values));
    }
}
