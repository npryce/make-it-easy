package com.natpryce.makeiteasy;

/**
 * An opaque "handle" that represents a property of of some type of object.
 * <p>
 * For example, if a Person object has a name property of type String, that
 * property would be represented by an instance of Property&lt;Person,String&gt;.
 *
 * @param <T> the type of object that has the property
 * @param <V> the type of the value of the property
 */
@SuppressWarnings("UnusedDeclaration")
public class Property<T, V> {
    public static <T, V> Property<T, V> newProperty() {
        return new Property<>();
    }
}
