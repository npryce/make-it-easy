package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import example.fruit.Fruit;
import example.fruit.FruitBowl;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static com.natpryce.makeiteasy.Property.newProperty;
import static example.fruit.makeiteasy.FruitMakers.Apple;
import static example.fruit.makeiteasy.FruitMakers.Banana;


/**
 * An example of how to define builders for properties that are collections.
 */
class FruitBowlMaker {

    static final Property<FruitBowl, Iterable<? extends Fruit>> contents = newProperty();
    static final Instantiator<FruitBowl> FruitBowl = lookup -> new FruitBowl(
            lookup.valueOf(contents, listOf(an(Apple), a(Banana)).value()));
}