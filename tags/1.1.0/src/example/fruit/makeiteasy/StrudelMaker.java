package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import com.natpryce.makeiteasy.Property;
import static com.natpryce.makeiteasy.Property.newProperty;
import com.natpryce.makeiteasy.PropertyLookup;
import example.fruit.Apple;
import example.fruit.Strudel;
import static example.fruit.makeiteasy.FruitMakers.Apple;

import static java.util.Arrays.asList;


/**
 * An example of how to define builders for properties that are collections.
 */
public class StrudelMaker {
    public static final Property<Strudel, Iterable<Apple>> apples = newProperty();

    public static final Instantiator<Strudel> Strudel = new Instantiator<Strudel>() {
        @Override
        public Strudel instantiate(PropertyLookup<Strudel> lookup) {
            return new Strudel(lookup.valueOf(apples, asList(make(an(Apple)))));
        }
    };
}
