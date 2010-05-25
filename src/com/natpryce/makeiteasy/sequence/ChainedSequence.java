package com.natpryce.makeiteasy.sequence;

import com.natpryce.makeiteasy.Donor;

/**
 * A sequence of values, each of which is calculated from the previous value
 * in the sequence.
 *
 * @param <T> the type of the value
 */
public abstract class ChainedSequence<T> implements Donor<T> {
    protected T nextValue;

    /**
     * Initialises the sequence, giving the first value.
     *
     * @param firstValue the first value in the sequence.
     */
    public ChainedSequence(T firstValue) {
        this.nextValue = firstValue;
    }

    @Override
    public T value() {
        T result = nextValue;
        nextValue = valueAfter(nextValue);
        return result;
    }

    /**
     * Return the value after a given value.
     *
     * @param prevValue the previous value
     * @return the value after prevValue in this sequence
     */
    protected abstract T valueAfter(T prevValue);
}
