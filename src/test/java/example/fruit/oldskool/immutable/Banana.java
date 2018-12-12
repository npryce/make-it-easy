package example.fruit.oldskool.immutable;

import example.fruit.Fruit;
import example.fruit.oldskool.BananaBuilder;

public final class Banana extends Fruit {
    private final double curve;
    private final double ripeness;

    public Banana(BananaBuilder bananaBuilder) {
        this.ripeness = bananaBuilder.ripeness;
        this.curve = bananaBuilder.curve;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public double curve() {
        return curve;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public double ripeness() {
        return ripeness;
    }
}
