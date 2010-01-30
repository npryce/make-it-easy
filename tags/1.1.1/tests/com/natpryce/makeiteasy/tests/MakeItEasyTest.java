package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import org.junit.Test;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MakeItEasyTest {
    public static class ThingToMake {
        public final String name;
        public final int age;

        public ThingToMake(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static final Property<ThingToMake, String> name = newProperty();
    public static final Property<ThingToMake, Integer> age = newProperty();

    public static Instantiator<ThingToMake> ThingToMake = new Instantiator<ThingToMake>() {
        @Override
        public ThingToMake instantiate(PropertyLookup<ThingToMake> lookup) {
            return new ThingToMake(lookup.valueOf(name, "Nemo"), lookup.valueOf(age, 99));
        }
    };

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

    public static class ThingContainer {
        public final ThingToMake thing;

        public ThingContainer(ThingToMake thing) {
            this.thing = thing;
        }
    }

    public static Property<ThingContainer, ThingToMake> thing = newProperty();

    public static Instantiator<ThingContainer> ThingContainer = new Instantiator<ThingContainer>() {
        @Override
        public ThingContainer instantiate(PropertyLookup<ThingContainer> lookup) {
            return new ThingContainer(lookup.valueOf(thing, make(a(ThingToMake))));
        }
    };

    @Test
    public void canUseMakersToInitialisePropertyValues() {
        ThingContainer container = make(a(ThingContainer, with(thing, a(ThingToMake, with(name, "foo")))));

        assertThat(container.thing.name, equalTo("foo"));
    }
}
