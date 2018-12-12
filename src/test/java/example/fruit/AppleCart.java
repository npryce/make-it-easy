package example.fruit;

import example.fruit.makeiteasy.immutable.Apple;

import java.util.HashSet;
import java.util.Set;


/**
 * An example of how to define builders for properties that are collections.
 *
 * @see example.fruit.makeiteasy.AppleCartMaker
 */
public class AppleCart {
    private final Set<Apple> apples = new HashSet<>();

    public AppleCart(Iterable<Apple> apples) {
        for (Apple apple : apples) {
            this.apples.add(apple);
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public int currentLoad() {
        return apples.size();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public void upset() {
        apples.clear();
    }
}
