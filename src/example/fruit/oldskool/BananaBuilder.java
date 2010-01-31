package example.fruit.oldskool;

import example.fruit.Banana;

public class BananaBuilder implements Builder<Banana> {
    private double ripeness = 0.0;
    private double curve = 0.5;

    private BananaBuilder() {}

    public static BananaBuilder aBanana() {
        return new BananaBuilder();
    }

    public Banana build() {
        Banana banana = new Banana(curve);
        banana.ripen(ripeness);
        return banana;
    }

    public BananaBuilder withRipeness(double ripeness){
        this.ripeness = ripeness;
        return this;
    }

    public BananaBuilder withCurve(double curve) {
        this.curve = curve;
        return this;
    }
    
    public BananaBuilder but() {
        return new BananaBuilder()
                .withRipeness(ripeness)
                .withCurve(curve);
    }
}