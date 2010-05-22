package com.natpryce.makeiteasy;

/**
 * Provides a value or values to other things.
 *
 * @param <T> the type of value donated
 */
public interface Donor<T> {
    /**
     * Donate the value.
     *
     * @return the value donated
     */
    T value();
}
