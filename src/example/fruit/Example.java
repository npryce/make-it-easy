package example.fruit;

import com.natpryce.makeiteasy.Maker;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static example.fruit.FruitMakers.Apple;
import static example.fruit.FruitMakers.Banana;
import static example.fruit.FruitMakers.*;


@SuppressWarnings({"UnusedDeclaration"})
public class Example {
    public static void main(String[] args) {
        Maker<Apple> appleWith2Leaves = an(Apple, with(2, leaves));
        Maker<Apple> ripeApple = an(Apple, like(appleWith2Leaves), with(ripeness, 0.9));
        Maker<Apple> unripeApple = an(Apple, like(appleWith2Leaves), with(ripeness, 0.125));
        
        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);
        
        Banana defaultBanana = make(a(Banana));
    }
}
