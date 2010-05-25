package example.donors;

import com.natpryce.makeiteasy.*;
import com.natpryce.makeiteasy.sequence.ChainedSequence;
import com.natpryce.makeiteasy.sequence.IndexedSequence;
import org.junit.Test;

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
        class IndexedNameSequence extends IndexedSequence<String> {
            @Override
            protected String valueAt(int index) {
                return Integer.toString(index);
            }
        }

        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, new IndexedNameSequence()));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);

        assertThat(thing0.name, equalTo("0"));
        assertThat(thing1.name, equalTo("1"));
    }

    @Test
    public void allocatingNamesByChain() {
        class NameSequence extends ChainedSequence<String> {
            NameSequence(String firstValue) {
                super(firstValue);
            }

            @Override
            protected String valueAfter(String prevValue) {
                return prevValue + "'";
            }
        }

        Maker<NamedThing> aNamedThing = a(NamedThing, with(name, new NameSequence("A")));

        NamedThing thing0 = make(aNamedThing);
        NamedThing thing1 = make(aNamedThing);

        assertThat(thing0.name, equalTo("A"));
        assertThat(thing1.name, equalTo("A'"));
    }
}