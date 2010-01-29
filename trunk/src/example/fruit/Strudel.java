package example.fruit;

import example.fruit.makeiteasy.StrudelMaker;

import java.util.Set;
import java.util.HashSet;


/**
 * An example of how to define builders for properties that are collections.
 *
 * @see {@link StrudelMaker}
 */
public class Strudel {
    private final Set<Apple> apples = new HashSet<Apple>();

    public Strudel(Iterable<Apple> apples) {
        for (Apple apple : apples) {
            this.apples.add(apple);
        }
    }

    public int howManyApples() {
        return apples.size();
    }
}
