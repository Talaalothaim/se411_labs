package se411.lab3;
import java.util.List;

public class App {

    /** Exercise 3a: unbounded wildcard - accepts a List of literally any type. */
    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.println("  - " + element);
        }
    }
 
    /**
     * Exercise 3b: upper-bounded wildcard - accepts List<Integer>, List<Double>,
     * List<Number>, etc. Reading as Number is safe; adding to the list is not,
     * which is why the compiler forbids it.
     */
    public static double sumNumbers(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue();
        }
        return total;
    }
 
    public static void main(String[] args) {
 
        System.out.println("=== Exercise 1: PrintableList ===");
 
        String[] cities = { "Riyadh", "Jeddah", "Dammam", "Abha" };
        PrintableList<String> cityList = new PrintableList<>(cities);
        System.out.println("Cities (" + cityList.size() + " items):");
        cityList.printList();
 
        Integer[] years = { 2023, 2024, 2025, 2026 };
        PrintableList<Integer> yearList = new PrintableList<>(years);
        System.out.println("Years:");
        yearList.printList();
 
        yearList.add(2027);
        System.out.println("After add(2027) -> " + yearList);
 
        System.out.println();
        System.out.println("=== Exercise 2: NumberBox ===");
 
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.setItem(10);
        System.out.println("Stored integer : " + intBox.getItem());
        System.out.println("10 + 5         = " + intBox.sumWith(5));
 
        NumberBox<Double> dblBox = new NumberBox<>(20.5);
        System.out.println("Stored double  : " + dblBox.getItem());
        System.out.println("20.5 + 4.5     = " + dblBox.sumWith(4.5));
        System.out.println("Mixed types    = " + dblBox.sumWith(intBox.getItem()));
 
        // NumberBox<String> bad = new NumberBox<>();  // won't compile: String is not a Number
 
        System.out.println();
        System.out.println("=== Exercise 3: Wildcards ===");
 
        List<Integer> intList = List.of(1, 2, 3, 4);
        List<Double> dblList = List.of(1.1, 2.2, 3.3);
 
        System.out.println("Any-type list (Strings from Exercise 1):");
        printList(cityList.getList());
 
        System.out.println("Integer list:");
        printList(intList);
 
        System.out.println("Double list:");
        printList(dblList);
 
        System.out.println("Sum of integer list : " + sumNumbers(intList));
        System.out.println("Sum of double list  : " + sumNumbers(dblList));
        System.out.println("Sum of year list    : " + sumNumbers(yearList.getList()));
    }
}