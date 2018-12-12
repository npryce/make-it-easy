package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import example.fruit.makeiteasy.immutable.Apple;
import example.fruit.makeiteasy.immutable.Banana;
import example.fruit.Fruit;

import static com.natpryce.makeiteasy.Property.newProperty;


class FruitMakers {
    static final Property<Fruit, Double> ripeness = newProperty();
    static final Property<Apple, Integer> leaves = newProperty();
    static final Property<Banana, Double> curve = newProperty();

    static final Instantiator<Apple> Apple = lookup -> {
        Apple apple = new Apple(lookup.valueOf(leaves, 2));
        apple.ripen(lookup.valueOf(ripeness, 0.0));
        return apple;
    };

    static final Instantiator<Banana> Banana = lookup -> {
        Banana banana = new Banana(lookup.valueOf(curve, 0.5));
        banana.ripen(lookup.valueOf(ripeness, 0.0));
        return banana;
    };
}
