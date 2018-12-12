package example.fruit.oldskool;

import example.fruit.oldskool.immutable.Banana;

public class BananaBuilder implements Builder<BananaBuilder, Banana> {
    public double ripeness = 0.0;
    public double curve = 0.5;

    private BananaBuilder() {
    }

    static Builder<BananaBuilder, Banana> aBanana() {
        return new BananaBuilder();
    }

    BananaBuilder withRipeness(double ripeness) {
        this.ripeness = ripeness;
        return instance();
    }

    BananaBuilder withCurve(double curve) {
        this.curve = curve;
        return instance();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public BananaBuilder but() {
        return new BananaBuilder()
                .withRipeness(ripeness)
                .withCurve(curve);
    }

    @Override
    public Banana build() {
        return new Banana(this);
    }

    @Override
    public BananaBuilder instance() {
        return this;
    }
}