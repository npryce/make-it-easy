package example.donors;

import com.natpryce.makeiteasy.*;
import com.natpryce.makeiteasy.sequence.ChainedSequence;
import com.natpryce.makeiteasy.sequence.IndexedSequence;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class DonorExample {
    public static class NamedThing {
        public final String name;

        public NamedThing(String name) {
            this.name = name;
        }
    }

    public static final Property<NamedThing,String> name = newProperty();

    public static final Instantiator<NamedThing> NamedThing = new Instantiator<NamedThing>() {
        @Override
        public NamedThing instantiate(PropertyLookup<NamedThing> lookup) {
            return new NamedThing(lookup.valueOf(name, "anonymous"));
        }
    };

    @Test
    public void allocatingUniqueNames() {
        class UUIDValue implements Donor<String> {
            @Override
            public String value() {
                return UUID.randomUUID().toString();
            }
        }

        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, new UUIDValue()));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);

        assertThat(thing0.name, not(equalTo(thing1.name)));
    }
    
    @Test
    public void allocatingNamesByIndex() {
        class NameSequence extends IndexedSequence<String> {
            @Override
            protected String valueAt(int index) {
                return Integer.toString(index);
            }
        }

        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, new NameSequence()));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);

        assertThat(thing0.name, equalTo("0"));
        assertThat(thing1.name, equalTo("1"));
    }

    @Test
    public void allocatingNamesByChain() {
        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, new ChainedSequence<String>() {
            protected String firstValue() { return "X"; }
            protected String valueAfter(String prevValue) { return prevValue + "'"; }
        }));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);
        NamedThing thing2 = make(aNamedThing);

        assertThat(thing0.name, equalTo("X"));
        assertThat(thing1.name, equalTo("X'"));
        assertThat(thing2.name, equalTo("X''"));
    }

    @Test
    public void allocatingNamesFromACollection() {
        SortedSet<String> names = new TreeSet<>();
        names.add("Bob");
        names.add("Alice");
        names.add("Carol");
        names.add("Dave");

        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, from(names)));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);
        NamedThing thing2 = make(aNamedThing);
        NamedThing thing3 = make(aNamedThing);

        assertThat(thing0.name, equalTo("Alice"));
        assertThat(thing1.name, equalTo("Bob"));
        assertThat(thing2.name, equalTo("Carol"));
        assertThat(thing3.name, equalTo("Dave"));
    }
}
