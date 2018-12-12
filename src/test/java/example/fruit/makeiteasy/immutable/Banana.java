package example.fruit.makeiteasy.immutable;

import example.fruit.Fruit;

public final class Banana extends Fruit {
    private final double curve;

    public Banana(double curve) {
        this.curve = curve;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public double curve() {
        return curve;
    }
}