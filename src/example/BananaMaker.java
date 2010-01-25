package example;

import com.natpryce.makeiteasy.Property;


public class BananaMaker extends FruitMaker<Banana> {
    public static final Property<Banana,Double> curve = new Property<Banana,Double>();

    public Banana make() {
        return new Banana(valueFor(curve, 0.5));
    }
}
