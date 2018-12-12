package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Donor;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.TreeSet;

import static com.natpryce.makeiteasy.MakeItEasy.from;
import static com.natpryce.makeiteasy.MakeItEasy.fromRepeating;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class SequenceTests {
    @Test
    public void sequenceFromCollection() {
        Donor<String> names = from(new TreeSet<>(asList("Bob", "Alice", "Carol", "Dave")));

        assertThat(names.value(), equalTo("Alice"));
        assertThat(names.value(), equalTo("Bob"));
        assertThat(names.value(), equalTo("Carol"));
        assertThat(names.value(), equalTo("Dave"));
    }

    @Test(expected = NoSuchElementException.class)
    public void sequenceFailsIfNoMoreElementsInCollection() {
        Donor<String> names = from(asList("A", "B"));

        assertThat(names.value(), equalTo("A"));
        assertThat(names.value(), equalTo("B"));

        names.value();
    }

    @Test
    public void repeatingSequenceFromCollection() {
        Donor<String> names = fromRepeating(asList("A", "B"));

        assertThat(names.value(), equalTo("A"));
        assertThat(names.value(), equalTo("B"));
        assertThat(names.value(), equalTo("A"));
        assertThat(names.value(), equalTo("B"));
    }

    @Test
    public void sequenceFromVarargs() {
        Donor<String> names = from("Alice", "Bob", "Carol", "Dave");

        assertThat(names.value(), equalTo("Alice"));
        assertThat(names.value(), equalTo("Bob"));
        assertThat(names.value(), equalTo("Carol"));
        assertThat(names.value(), equalTo("Dave"));
    }

    @Test
    public void repeatingSequenceFromVarargs() {
        Donor<String> names = fromRepeating("A", "B");

        assertThat(names.value(), equalTo("A"));
        assertThat(names.value(), equalTo("B"));
        assertThat(names.value(), equalTo("A"));
        assertThat(names.value(), equalTo("B"));
    }
}
