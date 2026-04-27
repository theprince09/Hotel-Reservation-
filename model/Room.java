package model;

import Interface.IRoom;
import enumerations.RoomType;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public final String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public final Double getRoomPrice() {
        return price;
    }

    @Override
    public final RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public final boolean isFree() {
        return this.price == 0.0;
    }

    @Override
    public String toString() {
        if (price != null && price > 0) {
            return "Room \n"
                    + "Number: " + roomNumber
                    + " Price: $" + price
                    + " Room Type: " + enumeration.toString().toLowerCase() + " bed room\n";
        } else {
            return "Free Room \n"
                    + "Number: " + roomNumber
                    + " Room Type: " + enumeration.toString().toLowerCase() + " bed room\n";
        }
    }
}
