package model;

import enumerations.RoomType;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString() {
        return "Free Room \n"
                + "Number: " + getRoomNumber() + " Price: $" + getRoomPrice()
                + " Room Type: " + getRoomType().toString().toLowerCase() + " bed room\n";
    }
}