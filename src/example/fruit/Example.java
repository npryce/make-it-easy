package example.fruit;

import com.natpryce.makeiteasy.Maker;

import static com.natpryce.makeiteasy.Maker.*;
import static example.fruit.builders.AppleMaker.*;
import static example.fruit.builders.BananaMaker.banana;
import static example.fruit.builders.FruitMaker.ripeness;


@SuppressWarnings({"UnusedDeclaration"})
public class Example {
    public static void main(String[] args) {
        Maker<Apple> ripeApple = an(apple, with(2, leaves), with(ripeness, 0.9));
        Maker<Apple> unripeApple = an(apple, like(ripeApple), with(ripeness, 0.125));

        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);
        
        Banana defaultBanana = make(a(banana));
    }
}
