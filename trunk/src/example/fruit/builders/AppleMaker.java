package example.fruit.builders;

import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyProvider;
import example.fruit.Apple;


public class AppleMaker extends FruitMaker<Apple> {
    public static final Property<Apple, Integer> leaves = new Property<Apple, Integer>();

    public static Maker<Apple> anApple(PropertyProvider<? super Apple>... properties) {
        return new AppleMaker(properties);
    }
    
    public Apple make() {
        Apple apple = new Apple(valueFor(leaves, 2));
        apple.ripen(valueFor(ripeness, 0.0));
        return apple;
    }
    
    private AppleMaker(PropertyProvider<? super Apple>... properties) {
        super(properties);
    }
}
