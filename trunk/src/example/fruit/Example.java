package example.fruit;

import com.natpryce.makeiteasy.Maker;

import static com.natpryce.makeiteasy.Maker.*;
import static example.fruit.FruitMakers.*;


@SuppressWarnings({"UnusedDeclaration"})
public class Example {
    public static void main(String[] args) {
        Maker<Apple> ripeApple = an(Apple, with(2, leaves), with(ripeness, 0.9));
        Maker<Apple> unripeApple = an(Apple, like(ripeApple), with(ripeness, 0.125));

        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);
        
        Banana defaultBanana = make(a(Banana));
    }
}
