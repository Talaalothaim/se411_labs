package lab05_exceptions;

public class InvalidAgeException extends Exception {
 
    private static final long serialVersionUID = 1L;
 
    /** The age that was rejected, kept so the caller can inspect it. */
    private final int age;
 
    public InvalidAgeException(int age) {
        super("Invalid age: " + age + ". Age must be 18 or higher.");
        this.age = age;
    }
 
    public InvalidAgeException(String message) {
        super(message);
        this.age = -1;
    }
 
    public int getAge() {
        return age;
    }
}
 