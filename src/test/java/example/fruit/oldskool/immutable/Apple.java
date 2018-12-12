package example.fruit.oldskool.immutable;

import example.fruit.Fruit;
import example.fruit.oldskool.AppleBuilder;

public final class Apple extends Fruit {
    private final int leaves;
    private final double ripeness;

    public Apple(AppleBuilder builder) {
        this.leaves = builder.leaves;
        this.ripeness = builder.ripeness;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public int numberOfLeaves() {
        return leaves;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public double ripeness() {
        return ripeness;
    }
}
