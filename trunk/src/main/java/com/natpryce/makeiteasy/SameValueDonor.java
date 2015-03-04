package com.natpryce.makeiteasy;

/**
 * Always gives the sale value.
 */
public class SameValueDonor<T> implements Donor<T> {
    private final T value;

    public SameValueDonor(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }
}
