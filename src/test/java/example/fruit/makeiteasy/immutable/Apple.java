package example.fruit.makeiteasy.immutable;

import example.fruit.Fruit;

public final class Apple extends Fruit {
    private final int leaves;

    public Apple(int leaves) {
        this.leaves = leaves;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public int numberOfLeaves() {
        return leaves;
    }
}