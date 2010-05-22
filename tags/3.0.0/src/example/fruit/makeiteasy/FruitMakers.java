package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import static com.natpryce.makeiteasy.Property.newProperty;
import com.natpryce.makeiteasy.PropertyLookup;
import example.fruit.Apple;
import example.fruit.Banana;
import example.fruit.Fruit;


public class FruitMakers {
    public static final Property<Fruit, Double> ripeness = newProperty();

    public static final Property<Apple, Integer> leaves = newProperty();

    public static final Property<Banana, Double> curve = newProperty();

    public static final Instantiator<Apple> Apple = new Instantiator<Apple>() {
        @Override
        public Apple instantiate(PropertyLookup<Apple> lookup) {
            Apple apple = new Apple(lookup.valueOf(leaves, 2));
            apple.ripen(lookup.valueOf(ripeness, 0.0));
            return apple;
        }
    };

    public static final Instantiator<Banana> Banana = new Instantiator<Banana>() {
        @Override
        public Banana instantiate(PropertyLookup<Banana> lookup) {
            Banana banana = new Banana(lookup.valueOf(curve, 0.5));
            banana.ripen(lookup.valueOf(ripeness, 0.0));
            return banana;
        }
    };
}
