package example.fruit;

public abstract class Fruit {
    private double ripeness = 0.0;

    public void ripen(double amount) {
        ripeness = Math.min(1.0, ripeness+amount);
    }

    public boolean isRipe() {
        return ripeness == 1.0;
    }
}
