package com.natpryce.makeiteasy.sequence;

import com.natpryce.makeiteasy.Donor;

import java.util.Iterator;

public class ElementsSequence<T> implements Donor<T> {
    private final Iterable<T> nextElements;
    private Iterator<T> current;

    public ElementsSequence(Iterable<T> firstElements, Iterable<T> nextElements) {
        this.nextElements = nextElements;
        this.current = firstElements.iterator();
    }

    @Override
    public T value() {
        if (!current.hasNext()) {
            current = nextElements.iterator();
        }

        return current.next();
    }
}
