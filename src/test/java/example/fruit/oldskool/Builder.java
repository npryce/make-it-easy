package example.fruit.oldskool;

interface Builder<B extends Builder<B, T>, T> {
    T build();
    B instance();
}