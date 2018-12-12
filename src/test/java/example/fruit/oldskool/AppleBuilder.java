package example.fruit.oldskool;


import example.fruit.oldskool.immutable.Apple;

public class AppleBuilder implements Builder<AppleBuilder, Apple> {
    public double ripeness = 0.0;
    public int leaves = 1;

    private AppleBuilder() {
    }

    static AppleBuilder anApple() {
        return new AppleBuilder();
    }

    AppleBuilder withRipeness(double ripeness) {
        this.ripeness = ripeness;
        return instance();
    }

    AppleBuilder withLeaves(int leaves) {
        this.leaves = leaves;
        return instance();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    AppleBuilder but() {
        return new AppleBuilder()
                .withRipeness(ripeness)
                .withLeaves(leaves);
    }

    @Override
    public Apple build() {
        return new Apple(this);
    }

    @Override
    public AppleBuilder instance() {
        return this;
    }
}
