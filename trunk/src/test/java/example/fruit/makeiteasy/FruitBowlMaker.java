package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import example.fruit.Fruit;
import example.fruit.FruitBowl;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static example.fruit.makeiteasy.FruitMakers.Apple;
import static example.fruit.makeiteasy.FruitMakers.Banana;


/**
 * An example of how to define builders for properties that are collections.
 */
public class FruitBowlMaker {
    public static final Property<FruitBowl, Iterable<? extends Fruit>> contents = newProperty();

    public static final Instantiator<FruitBowl> FruitBowl = new Instantiator<FruitBowl>() {
        @Override
        public FruitBowl instantiate(PropertyLookup<FruitBowl> lookup) {
            return new FruitBowl(
                    lookup.valueOf(contents, listOf(an(Apple), a(Banana)).value()));
        }
    };
}