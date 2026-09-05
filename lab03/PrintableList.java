package se411.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Exercise 1: a generic container that wraps a List<T> and can print it. */
public class PrintableList<T> {

    private List<T> items;

    /**
     * Builds the list from an array of items.
     * Arrays.asList() returns a fixed-size view backed by the array, so it is
     * copied into an ArrayList to keep the list modifiable later on.
     */
    
    public PrintableList(T[] items) {
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    public List<T> getList() {
        return items;
    }

    public void add(T item) {
        items.add(item);
    }

    public int size() {
        return items.size();
    }

    /** Prints every element on its own numbered line. */
    public void printList() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("  [" + i + "] " + items.get(i));
        }
    }

    @Override
    public String toString() {
        return "PrintableList" + items;
    }
}