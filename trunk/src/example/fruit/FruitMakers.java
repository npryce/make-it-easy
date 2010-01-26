package example.fruit;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class FruitMakers {
    public static final Property<Fruit,Double> ripeness = new Property<Fruit,Double>();

    public static final Property<Apple, Integer> leaves = new Property<Apple, Integer>();

    public static final Instantiator<Apple> Apple = new Instantiator<Apple>() {
        @Override
        public Apple instantiate(PropertyLookup<Apple> lookup) {
            Apple apple = new Apple(lookup.valueOf(leaves, 2));
            apple.ripen(lookup.valueOf(ripeness, 0.0));
            return apple;
        }
    };

    public static final Property<Banana,Double> curve = new Property<Banana,Double>();
    
    public static final Instantiator<Banana> Banana = new Instantiator<Banana>() {
        @Override
        public Banana instantiate(PropertyLookup<Banana> lookup) {
            Banana banana = new Banana(lookup.valueOf(curve, 0.5));
            banana.ripen(lookup.valueOf(ripeness, 0.0));
            return banana;
        }
    };
}
