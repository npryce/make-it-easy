package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import org.junit.Test;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class MakeItEasyTest {
    private static final Property<ThingToMake, String> name = newProperty();
    private static final Property<ThingToMake, Integer> age = newProperty();
    private static final Instantiator<ThingToMake> ThingToMake = lookup -> new ThingToMake(lookup.valueOf(name, "Nemo"), lookup.valueOf(age, 99));
    private static final Property<ThingContainer, ThingToMake> thing = newProperty();
    private static final Instantiator<ThingContainer> ThingContainer = lookup -> new ThingContainer(lookup.valueOf(thing, make(a(ThingToMake))));

    @Test
    public void usesDefaultPropertyValuesIfNoPropertiesSpecified() {
        ThingToMake madeThing = make(a(ThingToMake));

        assertThat(madeThing.name, equalTo("Nemo"));
        assertThat(madeThing.age, equalTo(99));
    }

    @Test
    public void overridesDefaultValuesWithExplicitProperties() {
        ThingToMake madeThing = make(a(ThingToMake, with(name, "Bob"), with(age, 10)));

        assertThat(madeThing.name, equalTo("Bob"));
        assertThat(madeThing.age, equalTo(10));

        ThingToMake differentName = make(a(ThingToMake, with(name, "Bill")));
        assertThat(differentName.name, equalTo("Bill"));
    }

    @Test
    public void canSpecifyNullPropertyValue() {
        ThingToMake madeThing = make(a(ThingToMake, withNull(name)));

        assertThat(madeThing.name, nullValue());
    }

    @Test
    public void canUseMakersToInitialisePropertyValues() {
        ThingContainer container = make(a(ThingContainer, with(thing, a(ThingToMake, with(name, "foo")))));

        assertThat(container.thing.name, equalTo("foo"));
    }

    @Test
    public void sharingDefinitionsAvoidsAliasingErrors() {
        Maker<ThingToMake> x99Maker = a(ThingToMake, with(name, "x"), with(age, 99));
        Maker<ThingToMake> x77Maker = x99Maker.but(with(age, 77));

        ThingToMake x99 = x99Maker.make();
        ThingToMake x77 = x77Maker.make();

        assertThat("x99.name", x99.name, equalTo("x"));
        assertThat("x77.name", x77.name, equalTo("x"));

        assertThat("x99.age", x99.age, equalTo(99));
        assertThat("x77.age", x77.age, equalTo(77));
    }

    static class ThingToMake {
        final String name;
        final int age;

        ThingToMake(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class ThingContainer {
        final ThingToMake thing;

        ThingContainer(ThingToMake thing) {
            this.thing = thing;
        }
    }
}
