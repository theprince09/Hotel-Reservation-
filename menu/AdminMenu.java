package menu;

import api.AdminResource;
import Interface.IRoom;
import model.Room;
import model.FreeRoom;
import enumerations.RoomType;
import model.Customer;

import java.util.*;

public class AdminMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final AdminResource ar = AdminResource.getInstance();

    public static void run() {

        boolean running = true;

        while (running) {

            printMenu();
            String choice = getValidChoice();

            switch (choice) {

                case "1":
                    seeAllCustomers();
                    break;

                case "2":
                    seeAllRooms();
                    break;

                case "3":
                    seeAllReservations();
                    break;

                case "4":
                    addRoom();
                    break;

                case "5":
                    running = false;
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
    }

    private static String getValidChoice() {
        while (true) {
            String input = sc.nextLine();
            if (input.matches("[1-5]"))
                return input;
            System.out.println("Invalid option. Enter 1-5:");
        }
    }

    private static void seeAllCustomers() {
        Collection customers = ar.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = ar.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeAllReservations() {
        ar.displayAllReservations();
    }

    private static void addRoom() {

        String number;

        while (true) {
            System.out.print("Enter room number: ");
            number = sc.nextLine();

            if (!number.isEmpty())
                break;
            System.out.println("Room number cannot be empty.");
        }

        double price;

        while (true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid price. Enter numeric value.");
            }
        }

        int type;

        while (true) {
            try {
                System.out.print("Enter room type (1 = SINGLE, 2 = DOUBLE): ");
                type = Integer.parseInt(sc.nextLine());

                if (type == 1 || type == 2)
                    break;

                System.out.println("Enter only 1 or 2.");

            } catch (Exception e) {
                System.out.println("Invalid input. Enter number.");
            }
        }

        RoomType roomType = (type == 1) ? RoomType.SINGLE : RoomType.DOUBLE;

        try {
            IRoom room;

            if (price == 0.0) {
                room = new FreeRoom(number, roomType);
            } else {
                room = new Room(number, price, roomType);
            }

            List<IRoom> roomList = new ArrayList<>();
            roomList.add(room);

            ar.addRoom(roomList);

            System.out.println("Room added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}