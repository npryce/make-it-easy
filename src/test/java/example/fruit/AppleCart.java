package example.fruit;

import java.util.HashSet;
import java.util.Set;


/**
 * An example of how to define builders for properties that are collections.
 *
 * @see example.fruit.makeiteasy.AppleCartMaker
 */
public class AppleCart {
    private final Set<Apple> apples = new HashSet<Apple>();

    public AppleCart(Iterable<Apple> apples) {
        for (Apple apple : apples) {
            this.apples.add(apple);
        }
    }

    public int currentLoad() {
        return apples.size();
    }

    public void upset() {
        apples.clear();
    }
}
