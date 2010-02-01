package example.fruit.makeiteasy;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import static com.natpryce.makeiteasy.Property.newProperty;
import com.natpryce.makeiteasy.PropertyLookup;
import example.fruit.Apple;
import example.fruit.Banana;
import example.fruit.Fruit;
import example.fruit.Tree;

import static java.util.Collections.emptyList;
import java.util.List;


/**
 * Java's broken type system makes it harder than it should be to define Properties and Instantiators
 * for generic types.
 * <p/>
 * To see the problem, inline the call to fruit() or emptyList() in the instantiator below.
 * As soon as the result of a generic method is passed as a parameter, instead of
 * assigned to a local variable or constant, the code stops compiling!
 * <p/>
 * The solution is to define local variables or constants to force the bindings of the property
 * type parameters, such as the apples and bananas constants. Or you can define properties and
 * instantiators for a specific instantiation of the generic type, as shown by the AppleTree
 * and BananaTree instantiators below.
 */
public class TreeMaker {
    public static <F extends Fruit> Property<Tree<F>, Iterable<? extends F>> fruit() {
        return newProperty();
    }

    public static <F extends Fruit> Instantiator<Tree<F>> Tree() {
        return new Instantiator<Tree<F>>() {
            @Override
            public Tree<F> instantiate(PropertyLookup<Tree<F>> lookup) {
                Property<Tree<F>, Iterable<? extends F>> fruit = fruit();
                List<F> noFruit = emptyList();

                return new Tree<F>(lookup.valueOf(fruit, noFruit));
            }
        };
    }

    public static final Instantiator<Tree<Apple>> AppleTree = Tree();
    public static final Property<Tree<Apple>, Iterable<? extends Apple>> apples = fruit();

    public static final Instantiator<Tree<Banana>> BananaTree = Tree();
    public static final Property<Tree<Banana>, Iterable<? extends Banana>> bananas = fruit();
}
