package example;

import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyProvider;
import com.natpryce.makeiteasy.PropertyValue;


public class AppleMaker extends FruitMaker<Apple> {
    public static final Property<Apple, Integer> leaves = new Property<Apple, Integer>();

    public static Maker<Apple> anApple(PropertyValue<? super Apple, ?>... properties) {
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
