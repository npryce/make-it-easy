package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import example.fruit.makeiteasy.immutable.Apple;
import example.fruit.AppleCart;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.listOf;
import static com.natpryce.makeiteasy.Property.newProperty;
import static example.fruit.makeiteasy.FruitMakers.Apple;


/**
 * An example of how to define builders for properties that are collections.
 */
class AppleCartMaker {
    static final Property<AppleCart, Iterable<Apple>> apples = newProperty();
    static final Instantiator<AppleCart> AppleCart = lookup -> new AppleCart(lookup.valueOf(apples, listOf(an(Apple))));
}
