package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.junit.MatcherAssert.assertThat;

// See Issue 2.
public class PropertyValueSharingTest {
    private final Property<Identity, String> name = newProperty();
    private final Instantiator<Identity> Identity = lookup -> new Identity(lookup.valueOf(name, "default-name"));
    private final Property<Identified, Identity> identity = newProperty();
    private final Instantiator<Identified> Identified = lookup -> new Identified(lookup.valueOf(identity, new Identity("default-identity")));
    private final Property<SecretAgent, List<Identity>> assumedIdentities = newProperty();
    private final Instantiator<SecretAgent> SecretAgent = lookup -> new SecretAgent(
            lookup.valueOf(assumedIdentities,
                    Collections.<Identity>emptyList()));

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

    class Identity {
        final String name;

        Identity(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Identity[" + System.identityHashCode(this) + ": " + name + "]";
        }
    }

    class Identified {
        final Identity identity;

        Identified(Identity identity) {
            this.identity = identity;
        }
    }

    class SecretAgent {
        final List<Identity> assumedIdentities;

        SecretAgent(List<Identity> assumedIdentities) {
            this.assumedIdentities = assumedIdentities;
        }
    }
}
