A tiny framework that makes it easy to write Test Data Builders in Java

[![Build Status](https://travis-ci.org/npryce/make-it-easy.svg?branch=master)](https://travis-ci.org/npryce/make-it-easy)

Test Data Builders are described in the book [Growing Object-Oriented Software, Guided by Tests](http://www.growing-object-oriented-software.com) by [Steve Freeman](http://www.m3p.co.uk) and [Nat Pryce](http://www.natpryce.com).  This library lets you write Test Data Builders with much less duplication and boilerplate code than the approach described in the book.

## Download ##

You can download from Maven Central with the artifact coordinates:

    com.natpryce:make-it-easy:4.0.1

## Example ##

Consider the following class hierarchy. This hierarchy illustrates a couple of complicating factors: there is an abstract base class and there is a property (Fruit.ripeness) that is not set via the constructor but by an operation of the Fruit class.


```java
public abstract class Fruit {
    private double ripeness = 0.0;

    public void ripen(double amount) {
        ripeness = Math.min(1.0, ripeness+amount);
    }

    public boolean isRipe() {
        return ripeness == 1.0;
    }
}

public class Apple extends Fruit {
    private int leaves;

    public Apple(int leaves) {
        this.leaves = leaves;
    }

    public int numberOfLeaves() {
        return leaves;
    }
}

public class Banana extends Fruit {
    public final double curve;

    public Banana(double curve) {
        this.curve = curve;
    }

    public double curve() {
        return curve;
    }
}
```

You can define Test Data Builders for Apples and Bananas with Make It Easy as follows:

```java
public class FruitMakers {
    public static final Property<Fruit,Double> ripeness = newProperty();

    public static final Property<Apple, Integer> leaves = newProperty();

    public static final Property<Banana,Double> curve = newProperty();

    public static final Instantiator<Apple> Apple = new Instantiator<Apple>() {
        @Override public Apple instantiate(PropertyLookup<Apple> lookup) {
            Apple apple = new Apple(lookup.valueOf(leaves, 2));
            apple.ripen(lookup.valueOf(ripeness, 0.0));
            return apple;
        }
    };

    public static final Instantiator<Banana> Banana = new Instantiator<Banana>() {
        @Override public Banana instantiate(PropertyLookup<Banana> lookup) {
            Banana banana = new Banana(lookup.valueOf(curve, 0.5));
            banana.ripen(lookup.valueOf(ripeness, 0.0));
            return banana;
        }
    };
}
```

And use them like this:

```java
Maker<Apple> appleWith2Leaves = an(Apple, with(2, leaves));
Maker<Apple> ripeApple = appleWith2Leaves.but(with(ripeness, 0.9));
Maker<Apple> unripeApple = appleWith2Leaves.but(with(ripeness, 0.125));

Apple apple1 = make(ripeApple);
Apple apple2 = make(unripeApple);

Banana defaultBanana = make(a(Banana));
Banana straightBanana = make(a(Banana, with(curve, 0.0)));
Banana squishyBanana = make(a(Banana, with(ripeness, 1.0)));
```

In contrast, doing so in the style documented in _Growing Object-Oriented Software, Guided by Tests_ would look like this:

```java
public interface Builder<T> {
    T build();
}

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

public class BananaBuilder implements Builder<Banana> {
    private double ripeness = 0.0;
    private double curve = 0.5;

    private BananaBuilder() {}

    public static BananaBuilder aBanana() {
        return new BananaBuilder();
    }

    public Banana build() {
        Banana apple = new Banana(curve);
        apple.ripen(ripeness);
        return apple;
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
```

And be used like this:

```java
AppleBuilder appleWith2Leaves = anApple().withLeaves(2);
AppleBuilder ripeApple = appleWith2Leaves.but().withRipeness(0.9);
AppleBuilder unripeApple = appleWith2Leaves.but().withRipeness(0.125);

Apple apple1 = ripeApple.build();
Apple apple2 = unripeApple.build();

Banana defaultBanana = aBanana().build();
Banana straightBanana = aBanana().withCurve(0.0).build();
Banana squishyBanana = aBanana().withRipeness(1.0).build();
```

As you can see, with Make It Easy you have to write a lot less duplicated and boilerplate code.  What duplication there is - in the declaration of anonymous Instantiator classes, for example - can be automatically inserted and refactored by modern IDEs.  (You could also factor out calls to Fruit.ripen to a private helper method, but I left them duplicated for clarity.)

The full code for this example is [in the Make It Easy repository](src/test/java/example/fruit).
