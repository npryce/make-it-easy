package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

// See Issue 2.
public class PropertyValueSharingTest {
    public class Identity {
        public String name;

        public Identity(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Identity[" + System.identityHashCode(this) + ": " + name + "]";
        }
    }

    public class Identified {
        public final Identity identity;

        public Identified(Identity identity) {
            this.identity = identity;
        }
    }

    public Property<Identity, String> name = newProperty();

    public Instantiator<Identity> Identity = new Instantiator<Identity>() {
        @Override
        public Identity instantiate(PropertyLookup<Identity> lookup) {
            return new Identity(lookup.valueOf(name, "default-name"));
        }
    };

    public Property<Identified, Identity> identity = newProperty();

    public Instantiator<Identified> Identified = new Instantiator<Identified>() {
        @Override
        public Identified instantiate(PropertyLookup<Identified> lookup) {
            return new Identified(lookup.valueOf(identity, new Identity("default-identity")));
        }
    };

    @Test
    public void aDistinctPropertyValueInstanceIsUsedForEachMadeObjectWhenPropertyIsDefinedWithAMaker() {
        Maker<Identified> anIdentified = an(Identified,
                with(identity, a(Identity, with(name, "original-name"))));

        Identified x = make(anIdentified);
        Identified y = make(anIdentified);

        assertThat(x.identity, not(sameInstance(y.identity)));
    }

    @Test
    public void canExplicitlyDeclareThatDifferentInstancesHaveTheSamePropertyValueInstance() {
        Maker<Identified> anIdentified = an(Identified,
                with(identity, theSame(Identity, with(name, "original-name"))));

        Identified x = make(anIdentified);
        Identified y = make(anIdentified);

        assertThat(x.identity, sameInstance(y.identity));
    }

    public class SecretAgent {
        public final List<Identity> assumedIdentities;

        public SecretAgent(List<Identity> assumedIdentities) {
            this.assumedIdentities = assumedIdentities;
        }
    }

    public Instantiator<SecretAgent> SecretAgent = new Instantiator<SecretAgent>() {
        @Override
        public SecretAgent instantiate(PropertyLookup<SecretAgent> lookup) {
            return new SecretAgent(
                    lookup.valueOf(assumedIdentities,
                                   Collections.<Identity>emptyList()));
        }
    };

    public Property<SecretAgent, List<Identity>> assumedIdentities = newProperty();

    @Test
    public void distinctCollectionElementsAreUsedForEachMadeObjectWhenElementsAreDefinedWithAMaker() {
        Maker<SecretAgent> anAgent = a(SecretAgent,
                with(assumedIdentities, listOf(
                        an(Identity, with(name, "jason bourne")),
                        an(Identity, with(name, "james bond")))));

        SecretAgent x = make(anAgent);
        SecretAgent y = make(anAgent);

        assertThat(x.assumedIdentities, not(sameInstance(y.assumedIdentities)));
        assertThat(x.assumedIdentities.get(0), not(sameInstance(y.assumedIdentities.get(0))));
        assertThat(x.assumedIdentities.get(1), not(sameInstance(y.assumedIdentities.get(1))));
    }

    @Test
    public void canDeclareThatElementsOfDifferentCollectionAreTheSame() {
        Maker<SecretAgent> anAgent = a(SecretAgent,
                with(assumedIdentities, listOf(
                        theSame(Identity, with(name, "austin powers")),
                        theSame(Identity, with(name, "harry palmer")))));

        SecretAgent x = make(anAgent);
        SecretAgent y = make(anAgent);

        assertThat(x.assumedIdentities, not(sameInstance(y.assumedIdentities)));
        assertThat(x.assumedIdentities.get(0), sameInstance(y.assumedIdentities.get(0)));
        assertThat(x.assumedIdentities.get(1), sameInstance(y.assumedIdentities.get(1)));
    }

    @Test
    public void canDeclareThatSameCollectionIsUsedForEveryMadeObject() {
        Maker<SecretAgent> anAgent = a(SecretAgent,
                with(assumedIdentities, theSame(listOf(
                        an(Identity, with(name, "jason bourne")),
                        an(Identity, with(name, "james bond"))))));

        SecretAgent x = make(anAgent);
        SecretAgent y = make(anAgent);

        assertThat(x.assumedIdentities, sameInstance(y.assumedIdentities));
    }
}
