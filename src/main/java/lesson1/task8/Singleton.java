package lesson1.task8;

public class Singleton {
    private static Singleton instance;
    // Private constructor to prevent instantiation from outside
    private Singleton() {
        // Initialization code here
    }

    // Static method to get the singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Other methods and variables can be added here
}
