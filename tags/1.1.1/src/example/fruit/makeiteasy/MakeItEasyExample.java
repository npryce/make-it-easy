package example.fruit.makeiteasy;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.setOf;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static com.natpryce.makeiteasy.MakeItEasy.listOf;
import com.natpryce.makeiteasy.Maker;
import example.fruit.Apple;
import example.fruit.Banana;
import example.fruit.Strudel;
import example.fruit.Tree;
import static example.fruit.makeiteasy.FruitMakers.Apple;
import static example.fruit.makeiteasy.FruitMakers.Banana;
import static example.fruit.makeiteasy.FruitMakers.curve;
import static example.fruit.makeiteasy.FruitMakers.leaves;
import static example.fruit.makeiteasy.FruitMakers.ripeness;
import static example.fruit.makeiteasy.StrudelMaker.Strudel;
import static example.fruit.makeiteasy.StrudelMaker.apples;
import static example.fruit.makeiteasy.TreeMaker.BananaTree;
import static example.fruit.makeiteasy.TreeMaker.bananas;


@SuppressWarnings({"UnusedDeclaration"})
public class MakeItEasyExample {
    public static void simpleFruitExample() {
        Maker<Apple> appleWith2Leaves = an(Apple, with(2, leaves));
        Maker<Apple> ripeApple = appleWith2Leaves.but(with(ripeness, 0.9));
        Maker<Apple> unripeApple = appleWith2Leaves.but(with(ripeness, 0.125));

        Apple apple1 = make(ripeApple);
        Apple apple2 = make(unripeApple);

        Banana defaultBanana = make(a(Banana));
        Banana straightBanana = make(a(Banana, with(curve, 0.0)));
        Banana squishyBanana = make(a(Banana, with(ripeness, 1.0)));
    }

    public static void strudelExampleIllustratingHowToMakeObjectsWithPropertiesThatAreCollections() {
        Strudel strudel = make(a(Strudel, with(apples, listOf(
            an(Apple, with(ripeness, 0.5)),
            an(Apple, with(ripeness, 0.35))
        ))));
    }

    // Yuck! But Java does not do type inference properly.
    public static void treeExampleIllustratingHowToMakeGenericObjects() {
        Tree<Banana> bananaTree = make(a(BananaTree,
            with(bananas, setOf(a(Banana), a(Banana), a(Banana)))));
    }
}
