package menu;

import api.HotelResource;
import model.Customer;
import model.Reservation;
import util.DateUtil;
import Interface.IRoom;
import enumerations.RoomType;
import java.util.*;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final HotelResource hr = HotelResource.getInstance();

    public static void run() {

        boolean running = true;

        while (running) {

            printMenu();
            String choice = getValidChoice();

            switch (choice) {

                case "1":
                    findAndReserveRoom();
                    break;

                case "2":
                    seeMyReservations();
                    break;

                case "3":
                    createAccount();
                    break;

                case "4":
                    AdminMenu.run();
                    break;

                case "5":
                    System.out.println("Exiting application...");
                    running = false;
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
    }

    private static String getValidChoice() {
        while (true) {
            String input = sc.nextLine();
            if (input.matches("[1-5]"))
                return input;
            System.out.println("Invalid option. Enter number between 1-5:");
        }
    }

    private static Date getValidDate(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return DateUtil.parseDate(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date. Use MM/dd/yyyy format.");
            }
        }
    }

    private static String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();

            if (email.contains("@") && email.contains(".")) {
                return email;
            }
            System.out.println("Invalid email. Try again.");
        }
    }

    private static IRoom getValidRoom() {
        while (true) {
            System.out.print("Enter room number: ");
            String roomNumber = sc.nextLine();

            IRoom room = hr.getRoom(roomNumber);

            if (room != null) {
                return room;
            }
            System.out.println("Room not found. Try again.");
        }
    }

    private static void findAndReserveRoom() {

        Date checkIn = getValidDate("Enter check-in date (dd/MM/yyyy): ");
        Date checkOut = getValidDate("Enter check-out date (dd/MM/yyyy): ");

        if (!DateUtil.isValidRange(checkIn, checkOut)) {
            System.out.println("Check-out date must be after check-in date...");
            return;
        }

        Collection<IRoom> availableRooms = hr.findARoom(checkIn, checkOut);

        if (availableRooms.isEmpty()) {

            System.out.println("No rooms available for given dates.");

            Calendar cal = Calendar.getInstance();

            cal.setTime(checkIn);
            cal.add(Calendar.DATE, 7);
            Date newCheckIn = cal.getTime();

            cal.setTime(checkOut);
            cal.add(Calendar.DATE, 7);
            Date newCheckOut = cal.getTime();

            Collection<IRoom> recommendedRooms = hr.findARoom(newCheckIn, newCheckOut);

            if (!recommendedRooms.isEmpty()) {
                System.out.println("\nRecommended Rooms after 7 days:");
                System.out.println("New Dates: " + newCheckIn + " to " + newCheckOut);

                recommendedRooms.forEach(System.out::println);
                System.out.print("Would you like to book for these new dates? (y/n): ");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("y")) {

                    String email = getValidEmail();

                    Customer customer = hr.getCustomer(email);

                    if (customer == null) {
                        System.out.println("Currently u have no account. Please create account first.");
                        return;
                    }

                    IRoom room = getValidRoom();

                    try {
                        Reservation reservation = hr.bookARoom(email, room, newCheckIn, newCheckOut);

                        System.out.println("Reservation successful!");
                        System.out.println(reservation);

                    } catch (Exception e) {
                        System.out.println("Booking failed: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No rooms available even after 7 days...");
            }

            return;
        }

        System.out.println("Available Rooms:");
        availableRooms.forEach(System.out::println);

        System.out.print("Would you like to book a room? (y/n): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {

            String email = getValidEmail();

            Customer customer = hr.getCustomer(email);

            if (customer == null) {
                System.out.println("No account found. Please create account first.");
                return;
            }

            IRoom room = getValidRoom();

            try {
                Reservation reservation = hr.bookARoom(email, room, checkIn, checkOut);

                System.out.println("Reservation successful!");
                System.out.println(reservation);

            } catch (Exception e) {
                System.out.println("Booking failed: " + e.getMessage());
            }

        }
    }

    private static void seeMyReservations() {

        String email = getValidEmail();

        Collection<Reservation> reservations = hr.getCustomerReservations(email);

        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private static void createAccount() {

        try {
            String email = getValidEmail();

            System.out.print("Enter first name: ");
            String firstName = sc.nextLine();

            System.out.print("Enter last name: ");
            String lastName = sc.nextLine();

            hr.createCustomer(email, firstName, lastName);

            System.out.println("Account created successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}