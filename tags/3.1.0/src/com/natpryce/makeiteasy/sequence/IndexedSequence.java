package com.natpryce.makeiteasy.sequence;

import com.natpryce.makeiteasy.Donor;

/**
 * A sequence of values, each of which is calculated from its index in
 * the sequence.
 *
 * @param <T> the type of the value
 */
public abstract class IndexedSequence<T> implements Donor<T> {
    private int index = 0;

    @Override
    public T value() {
        T value = valueAt(index);
        index++;
        return value;
    }

    /**
     * Return the value at a given index.
     *
     * @param index the index of the value
     * @return the value at that index
     */
    protected abstract T valueAt(int index);
}
