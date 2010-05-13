package com.natpryce.makeiteasy.tests;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import org.junit.Test;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MutableMakeeTest {
    public class Identity {
        public String name;

        public Identity(String name) {
            this.name = name;
        }
    }

    public class Identified {
        public final Identity identity;

        public Identified(Identity identity) {
            this.identity = identity;
        }
    }

    public Property<Identity,String> name = newProperty();

    public Instantiator<Identity> CanChangeName = new Instantiator<Identity>() {
        @Override
        public Identity instantiate(PropertyLookup<Identity> lookup) {
            return new Identity(lookup.valueOf(name,"default-name"));
        }
    };

    public Property<Identified,Identity> identity = newProperty();

    public Instantiator<Identified> Identified = new Instantiator<Identified>() {
        @Override
        public Identified instantiate(PropertyLookup<Identified> lookup) {
            return new Identified(lookup.valueOf(identity, new Identity("default-identity")));
        }
    };

    @Test
    public void aDistinctPropertyValueInstanceIsUsedForEachMadeObjectWhenPropertyIsDefinedWithAMaker() {
        Maker<Identified> anIdentified = an(Identified,
                with(identity, a(CanChangeName, with(name, "original-name"))));
        
        Identified x = make(anIdentified);
        Identified y = make(anIdentified);
        
        x.identity.name = "new-name";
        
        assertThat(y.identity.name, equalTo("original-name"));
    }
}
