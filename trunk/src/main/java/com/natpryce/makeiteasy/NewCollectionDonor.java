package com.natpryce.makeiteasy;

import java.util.Collection;

import static java.util.Arrays.asList;

public abstract class NewCollectionDonor<T extends Collection<E>, E> implements Donor<T> {
    private final Iterable<? extends Donor<? extends E>> elementGivers;

    public NewCollectionDonor(Donor<? extends E>... elementDonors) {
        this(asList(elementDonors));
    }

    public NewCollectionDonor(Iterable<? extends Donor<? extends E>> elementGivers) {
        this.elementGivers = elementGivers;
    }

    @Override
    public T value() {
        T newCollection = newCollection();
        for (Donor<? extends E> elementMaker : elementGivers) {
            newCollection.add(elementMaker.value());
        }
        return newCollection;
    }

    protected abstract T newCollection();
}
