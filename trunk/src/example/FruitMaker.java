package example;

import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyProvider;


public abstract class FruitMaker<T extends Fruit> extends Maker<T> {
    public static final Property<Fruit,Double> ripeness = new Property<Fruit,Double>();
    
    protected FruitMaker(PropertyProvider<? super T>... propertyProviders) {
        super(propertyProviders);
    }
}
