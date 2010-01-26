package example.fruit.builders;

import com.natpryce.makeiteasy.Maker;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyProvider;
import example.fruit.Banana;


public class BananaMaker extends FruitMaker<Banana> {
    public static final Property<Banana,Double> curve = new Property<Banana,Double>();

    private BananaMaker(PropertyProvider<? super Banana>... propertyProviders) {
        super(propertyProviders);
    }

    public Banana make() {
        return new Banana(valueFor(curve, 0.5));
    }

    public static Maker<Banana> aBanana(PropertyProvider<Banana>... propertyProviders) {
        return new BananaMaker(propertyProviders);
    }
}
