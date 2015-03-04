package example.fruit;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


public class Tree<T extends Fruit> implements Iterable<T> {
    private final Set<T> fruit = new HashSet<T>();

    public Tree(Iterable<? extends T> fruit) {
        for (T f : fruit) {
            this.fruit.add(f);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return fruit.iterator();
    }
}
