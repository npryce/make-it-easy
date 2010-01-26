package example.fruit;

public class Banana extends Fruit {
    public final double curve;

    public Banana(double curve) {
        this.curve = curve;
    }

    public double curve() {
        return curve;
    }
}
