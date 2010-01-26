package example.fruit.builders;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import example.fruit.Fruit;


public abstract class FruitMaker<T extends Fruit> implements Instantiator<T> {
    public static final Property<Fruit,Double> ripeness = new Property<Fruit,Double>();
}
