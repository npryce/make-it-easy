package example.fruit;

import java.util.Stack;


/**
 * An example of how to define builders for properties that are collections.
 * Unlike the {@link AppleCart}, a FruitBowl can contain any kind of fruit.
 *
 * @see {@link example.fruit.makeiteasy.FruitBowlMaker}
 */
public class FruitBowl {
    private final Stack<Fruit> contents = new Stack<Fruit>();

    public FruitBowl(Iterable<? extends Fruit> contents) {
        for (Fruit fruit : contents) {
            this.contents.add(fruit);
        }
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public Fruit takeOne() {
        return contents.pop();
    }
}
