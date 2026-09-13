package lab05;
import lab05_exceptions.InvalidAgeException;

public class ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== Exercise 1: validateAge ===");

        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught -> " + e.getMessage());
        }

        try {
            validateAge(25);
        } catch (InvalidAgeException e) {
            System.out.println("Caught -> " + e.getMessage());
        }

        System.out.println("Program finished normally.");
    }
	
	
	public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException(age);
        }
        System.out.println("Age valid message. (" + age + " is 18 or higher)");
    }
}
