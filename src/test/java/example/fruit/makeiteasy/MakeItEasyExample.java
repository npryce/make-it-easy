package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Maker;
import example.fruit.*;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static example.fruit.makeiteasy.AppleCartMaker.AppleCart;
import static example.fruit.makeiteasy.AppleCartMaker.apples;
import static example.fruit.makeiteasy.FruitBowlMaker.FruitBowl;
import static example.fruit.makeiteasy.FruitBowlMaker.contents;
import static example.fruit.makeiteasy.FruitMakers.*;
import static example.fruit.makeiteasy.TreeMaker.BananaTree;
import static example.fruit.makeiteasy.TreeMaker.bananas;


@SuppressWarnings({"UnusedDeclaration"})
public class MakeItEasyExample {
    public static void howToMakeSimpleObjects() {
        Maker<Apple> appleWith2Leaves = an(Apple, with(2, leaves));
        Maker<Apple> ripeApple = appleWith2Leaves.but(with(ripeness, 0.9));
        Maker<Apple> unripeApple = appleWith2Leaves.but(with(ripeness, 0.125));

        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);

        Banana defaultBanana = make(a(Banana));
        Banana straightBanana = make(a(Banana, with(curve, 0.0)));
        Banana squishyBanana = make(a(Banana, with(ripeness, 1.0)));
    }

    public static void howToMakeObjectsWithPropertiesThatAreCollections() {
        AppleCart cart = make(a(AppleCart, with(apples, listOf(
            an(Apple, with(ripeness, 0.5)),
            an(Apple, with(ripeness, 0.35))
        ))));

        FruitBowl bowl = make(a(FruitBowl, with(contents, listOf(
                an(Apple, with(2, leaves)),
                an(Apple, with(3, leaves)),
                a(Banana, with(ripeness, 0.25)),
                a(Banana, with(ripeness, 0.99))
        ))));
    }

    // Yuck! But Java does not do type inference properly.
    public static void howToMakeGenericObjects() {
        Tree<Banana> bananaTree = make(a(BananaTree,
            with(bananas, setOf(a(Banana), a(Banana), a(Banana)))));
    }
}
