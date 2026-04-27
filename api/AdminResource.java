package api;

import java.util.*;
import model.*;
import service.*;
import Interface.IRoom;

public class AdminResource {

    private static AdminResource instance = new AdminResource();
    private static CustomerService cS = CustomerService.getInstance();
    private static ReservationService rS = ReservationService.getInstance();

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return cS.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        if (rooms == null)
            return;

        for (IRoom room : rooms) {
            rS.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return rS.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return cS.getAllCustomers();
    }

    public void displayAllReservations() {
        rS.printAllReservation();
    }
}