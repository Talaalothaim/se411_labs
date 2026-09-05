package se411.lab3;

/**
 * Exercise 2: a generic wrapper limited by a bounded type parameter.
 * T can only be Number or one of its subclasses (Integer, Double, Long, ...).
 */
public class NumberBox<T extends Number> {

    private T item;

    public NumberBox() {
    }

    public NumberBox(T item) {
        this.item = item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    /**
     * Adds another number to the stored one.
     * The bound on T is what makes doubleValue() legal here: the compiler
     * already knows every T is a Number.
     */
    public double sumWith(Number other) {
        if (item == null || other == null) {
            throw new IllegalStateException("Cannot sum: the box or the argument is empty.");
        }
        return item.doubleValue() + other.doubleValue();
    }

    @Override
    public String toString() {
        return "NumberBox(" + item + ")";
    }
}