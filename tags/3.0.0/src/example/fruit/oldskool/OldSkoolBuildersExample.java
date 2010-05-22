package example.fruit.oldskool;

import example.fruit.Apple;
import example.fruit.Banana;

import static example.fruit.oldskool.AppleBuilder.anApple;
import static example.fruit.oldskool.BananaBuilder.aBanana;


@SuppressWarnings({"UnusedDeclaration"})
public class OldSkoolBuildersExample {
    public static void howToMakeSimpleObjects() {
        AppleBuilder appleWith2Leaves = anApple().withLeaves(2);
        AppleBuilder ripeApple = appleWith2Leaves.but().withRipeness(0.9);
        AppleBuilder unripeApple = appleWith2Leaves.but().withRipeness(0.125);
        
        Apple apple1 = ripeApple.build();
        Apple apple2 = unripeApple.build();

        Banana defaultBanana = aBanana().build();
    }
}