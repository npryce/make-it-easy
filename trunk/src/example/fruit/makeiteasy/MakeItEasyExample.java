package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Maker;
import example.fruit.Apple;
import example.fruit.Banana;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static example.fruit.makeiteasy.FruitMakers.Apple;
import static example.fruit.makeiteasy.FruitMakers.Banana;
import static example.fruit.makeiteasy.FruitMakers.*;


@SuppressWarnings({"UnusedDeclaration"})
public class MakeItEasyExample {
    public static void main(String[] args) {
        Maker<Apple> appleWith2Leaves = an(Apple, with(2, leaves));
        Maker<Apple> ripeApple = appleWith2Leaves.but(with(ripeness, 0.9));
        Maker<Apple> unripeApple = appleWith2Leaves.but(with(ripeness, 0.125));
        
        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);
        
        Banana defaultBanana = make(a(Banana));
    }
}
