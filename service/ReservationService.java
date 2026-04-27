package service;

import model.Customer;
import model.Reservation;
import Interface.IRoom;
import java.util.*;

public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private Map<String, IRoom> rooms = new HashMap<>();
    private List<Reservation> reserve = new ArrayList<>();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (room.getRoomPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room already booked");
        }
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (customer == null || room == null) {
            throw new IllegalArgumentException("Customer/Room cannot be null");
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();
        if (checkInDate.before(today)) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date ");
        }
        if (!checkOutDate.after(checkInDate)) {
            throw new IllegalArgumentException("Check-out must be after check-in");
        }

        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room is not available at this time");
        }

        Reservation temp = new Reservation(customer, room, checkInDate, checkOutDate);
        reserve.add(temp);
        return temp;
    }

    private boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {

        for (Reservation res : reserve) {

            if (room.equals(res.getRoom())
                    && checkInDate.before(res.getCheckOutDate())
                    && checkOutDate.after(res.getCheckInDate())) {

                return false;
            }
        }

        return true;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        List<IRoom> freeRoom = new ArrayList<>();

        if (reserve.isEmpty()) {
            freeRoom.addAll(rooms.values());
            return freeRoom;
        }

        for (IRoom room : rooms.values()) {

            boolean empty = false;

            for (Reservation res : reserve) {

                if (room.equals(res.getRoom())
                        && checkInDate.before(res.getCheckOutDate())
                        && checkOutDate.after(res.getCheckInDate())) {

                    empty = true;
                    break;
                }
            }

            if (!empty) {
                freeRoom.add(room);
            }
        }

        return freeRoom;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        List<Reservation> result = new ArrayList<>();

        if (customer == null) {
            return result;
        }

        for (Reservation temp : reserve) {
            if (temp.getCustomer() != null
                    && temp.getCustomer().getEmail().equals(customer.getEmail())) {
                result.add(temp);
            }
        }

        return result;
    }

    public void printAllReservation() {
        if (reserve.isEmpty()) {
            System.out.println("No reservation....");
            return;
        }
        for (Reservation temp : reserve) {
            System.out.println(temp);
        }
    }

    public Collection<Reservation> getAllReservations() {
        return reserve;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

}
