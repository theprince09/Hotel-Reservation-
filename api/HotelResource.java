package api;

import java.util.*;
import model.*;
import service.*;
import Interface.IRoom;

public class HotelResource {

    private static HotelResource instance = new HotelResource();
    private static CustomerService cS = CustomerService.getInstance();
    private static ReservationService rS = ReservationService.getInstance();

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return cS.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        cS.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return rS.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer temp = cS.getCustomer(customerEmail);
        if (temp == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return rS.reserveARoom(temp, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer temp = cS.getCustomer(customerEmail);
        if (temp == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return rS.getCustomerReservations(temp);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return rS.findRooms(checkIn, checkOut);
    }
}
