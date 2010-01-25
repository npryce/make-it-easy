package example;

import com.natpryce.makeiteasy.Maker;
import static example.AppleMaker.anApple;
import static example.AppleMaker.leaves;
import static example.FruitMaker.ripeness;


@SuppressWarnings({"UnusedDeclaration"})
public class Example {
    public static void main(String[] args) {
        Maker<Apple> ripeApple = anApple(Maker.with(2, leaves), Maker.with(ripeness, 0.9));
        Maker<Apple> unripeApple = anApple(Maker.with(1, leaves), Maker.with(ripeness, 0.125));

        Apple apple1 = Maker.make(ripeApple);
        Apple apple2 = Maker.make(unripeApple);
    }
}
