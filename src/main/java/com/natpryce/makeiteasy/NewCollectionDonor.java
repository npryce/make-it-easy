package com.natpryce.makeiteasy;

import java.util.Collection;

import static java.util.Arrays.asList;

public abstract class NewCollectionDonor<T extends Collection<E>, E> implements Donor<T> {
    private final Iterable<? extends Donor<? extends E>> elementDonors;

    @SafeVarargs
    NewCollectionDonor(Donor<? extends E>... elementDonors) {
        this(asList(elementDonors));
    }

    private NewCollectionDonor(Iterable<? extends Donor<? extends E>> elementDonors) {
        this.elementDonors = elementDonors;
    }

    @Override
    public T value() {
        T newCollection = newCollection();
        for (Donor<? extends E> elementDonor : elementDonors) {
            newCollection.add(elementDonor.value());
        }
        return newCollection;
    }

    protected abstract T newCollection();
}
