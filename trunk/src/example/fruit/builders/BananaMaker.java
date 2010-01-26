package example.fruit.builders;

import com.natpryce.makeiteasy.*;
import example.fruit.Banana;


public class BananaMaker extends FruitMaker<Banana> {
    public static final Property<Banana,Double> curve = new Property<Banana,Double>();

    @Override
    public Banana instantiate(PropertyLookup<Banana> properties) {
        return new Banana(properties.valueOf(curve, 0.5));
    }

    public static final Instantiator<Banana> banana = new BananaMaker();
}
