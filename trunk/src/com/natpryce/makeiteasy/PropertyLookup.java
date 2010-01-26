package com.natpryce.makeiteasy;

public interface PropertyLookup<T> {
    <V> V valueOf(Property<? super T,V> property, V defaultValue); 
}
