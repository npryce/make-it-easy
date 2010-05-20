package com.natpryce.makeiteasy;

public abstract class SequenceDonor<T> {
    private long count = 0;

    public T value() {
        return valueWithIndex(count++);
    }

    protected abstract T valueWithIndex(long l);
}
