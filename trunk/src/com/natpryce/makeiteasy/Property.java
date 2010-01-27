package com.natpryce.makeiteasy;

@SuppressWarnings("UnusedDeclaration")
public class Property<T,V> {
    public static <T,V> Property<T,V> newProperty() {
        return new Property<T,V>();
    }
}
