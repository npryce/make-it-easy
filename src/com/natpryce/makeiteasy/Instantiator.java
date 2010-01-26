package com.natpryce.makeiteasy;


public interface Instantiator<T> {
    T instantiate(PropertyLookup<T> lookup);
}
