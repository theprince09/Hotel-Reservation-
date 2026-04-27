
import model.Customer;
import menu.MainMenu;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Reservation Application!");
        MainMenu.run();
        // try {
        // Customer customer1 = new Customer("first", "second", "prince@gmail.com");
        // System.out.println(customer1.toString());
        // } catch (IllegalArgumentException e) {
        // System.err.println("Not working "+ e.getMessage());
        // }

        // try {
        // Customer customer1 = new Customer("John", "Doe", "email");
        // System.out.println(customer1.toString());
        // } catch (IllegalArgumentException e) {
        // System.err.println("Not working cuz: " + e.getMessage());
        // }

    }
}