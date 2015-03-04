package com.natpryce.makeiteasy;

/**
 * Looks up property values.
 * 
 * @param <T> type type of object for which the properties apply.
 */
public interface PropertyLookup<T> {
    /**
     *
     * @param property the property for which a value will be returned
     * @param defaultValue the default value to use if no value can be found
     * @param <V> the type of the value
     * @return the value for the given property, or <var>defaultValue</var> if no value can be found.
     */
    <V> V valueOf(Property<? super T,V> property, V defaultValue);
    
    /**
     *
     * @param property the property for which a value will be returned
     * @param defaultValueDonor an object that can provide the default value to use if no value can be found
     * @param <V> the type of the value
     * @return the value for the given property, or <var>defaultValueDonor.value()</var> if no value can be found.
     */
    <V> V valueOf(Property<? super T,V> property, Donor<? extends V> defaultValueDonor);
}
