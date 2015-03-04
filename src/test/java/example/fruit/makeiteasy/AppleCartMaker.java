package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import example.fruit.Apple;
import example.fruit.AppleCart;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.listOf;
import static com.natpryce.makeiteasy.Property.newProperty;
import static example.fruit.makeiteasy.FruitMakers.Apple;


/**
 * An example of how to define builders for properties that are collections.
 */
public class AppleCartMaker {
    public static final Property<AppleCart, Iterable<Apple>> apples = newProperty();

    public static final Instantiator<AppleCart> AppleCart = new Instantiator<AppleCart>() {
        @Override
        public AppleCart instantiate(PropertyLookup<AppleCart> lookup) {
            return new AppleCart(lookup.valueOf(apples, listOf(an(Apple))));
        }
    };
}
