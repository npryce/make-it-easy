package example.fruit.builders;

import com.natpryce.makeiteasy.*;
import example.fruit.Apple;


public class AppleMaker extends FruitMaker<Apple> {
    public static final Property<Apple, Integer> leaves = new Property<Apple, Integer>();

    public Apple instantiate(PropertyLookup<Apple> properties) {
        Apple apple = new Apple(properties.valueOf(leaves, 2));
        apple.ripen(properties.valueOf(ripeness, 0.0));
        return apple;
    }

    public static final Instantiator<Apple> apple = new AppleMaker();
}
