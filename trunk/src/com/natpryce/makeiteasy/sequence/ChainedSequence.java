package com.natpryce.makeiteasy.sequence;

import com.natpryce.makeiteasy.Donor;

/**
 * A sequence of values, each of which is calculated from the previous value
 * in the sequence.
 *
 * @param <T> the type of the value
 */
public abstract class ChainedSequence<T> implements Donor<T> {
    private T prevValue = null;

    @Override
    public T value() {
        T result = (prevValue == null) ? firstValue() : valueAfter(prevValue);
        prevValue = result;
        return result;
    }
    
    /**
     * Returns the first value in the sequence.
     * The result must not be null.
     *
     * @return the first value.
     */
    abstract protected T firstValue();

    /**
     * Return the value after a given value.
     * The result must not be null.
     *
     * @param prevValue the previous value
     * @return the value after prevValue in this sequence.
     */
    abstract protected T valueAfter(T prevValue);
}
