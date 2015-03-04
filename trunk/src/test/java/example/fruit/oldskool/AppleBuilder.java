package example.fruit.oldskool;

import example.fruit.Apple;

public class AppleBuilder implements Builder<Apple> {
    private double ripeness = 0.0;
    private int leaves = 1;

    private AppleBuilder() {}

    public static AppleBuilder anApple() {
        return new AppleBuilder();
    }

    public Apple build() {
        Apple apple = new Apple(leaves);
        apple.ripen(ripeness);
        return apple;
    }

    public AppleBuilder withRipeness(double ripeness){
        this.ripeness = ripeness;
        return this;
    }

    public AppleBuilder withLeaves(int leaves) {
        this.leaves = leaves;
        return this;
    }

    public AppleBuilder but() {
        return new AppleBuilder()
                .withRipeness(ripeness)
                .withLeaves(leaves);
    }
}
