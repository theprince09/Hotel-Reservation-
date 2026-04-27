🏨 Hotel Reservation Application



A Java-based command line hotel reservation system built using Object-Oriented Programming principles.
This application allows users to search, book, and manage hotel rooms, while admins can manage rooms, customers, and reservations.

🚀 Features
👤 Customer Features
Create a new account (with email validation)
Search for available rooms by date
Book a room
View all personal reservations

🛠️ Admin Features
View all customers
View all rooms
View all reservations
Add new rooms (Paid / Free)

🧠 OOP Concepts Used
This project strictly follows Object-Oriented Design principles:

✅ Encapsulation
All model class fields are private
Access via public getters
Data integrity maintained

✅ Abstraction
IRoom interface defines room behavior

✅ Inheritance
FreeRoom extends Room

✅ Polymorphism
IRoom reference is used for both:
Room
FreeRoom

📁 Project Structure
src/
│
├── api/
│   ├── AdminResource.java
│   └── HotelResource.java
│
├── menu/
│   ├── MainMenu.java
│   └── AdminMenu.java
│
├── model/
│   ├── Customer.java
│   ├── Room.java
│   ├── FreeRoom.java
│   └── Reservation.java
│
├── service/
│   ├── CustomerService.java
│   └── ReservationService.java
│
├── util/
│   └── DateUtil.java
│
└── Driver.java
🏗️ Core Components
🧍 Customer
Stores user details (first name, last name, email)
Email validated using regex

🛏️ Room
Represents a hotel room
Has:
Room Number
Price
Room Type (SINGLE / DOUBLE)

🆓 FreeRoom
Extends Room
Price is always 0
Displayed as Free Room in CLI

📅 Reservation
Links:
Customer
Room
Check-in & Check-out dates

⚙️ Services
🔹 CustomerService
Stores and manages all customers
Ensures unique email IDs

🔹 ReservationService
Stores:
Rooms (Map)
Reservations (List)
Handles:
Room availability
Booking logic
Double booking prevention

✔ Includes a private helper method for availability check:

private boolean isRoomAvailable(...)
📊 Data Structures Used
Map<String, IRoom> → Store rooms
List<Reservation> → Store reservations
Collection → Return data

🧪 Validations Implemented
📧 Customer Validation
Email format checked using regex
Invalid emails throw exception

🛏️ Room Validation
Room number cannot be empty
Room number must be unique
Price must be numeric & non-negative
Room type restricted to SINGLE / DOUBLE

📅 Date Validation
Check-in cannot be in the past
Check-out must be after check-in
Minimum stay = 1 day
Invalid date formats handled safely

🔐 Reservation Logic
Prevents double booking
Checks overlapping dates
Returns only available rooms
Suggests rooms after 7 days if none available

💻 CLI Features
Menu-driven interface using Scanner
Input validation loops (no crashes)
Clear error messages
Uses switch-case navigation

🧯 Error Handling
Extensive use of try-catch
Prevents application crashes
Prompts user to re-enter correct input

🧪 How to Run
1️⃣ Compile
javac -d . (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
2️⃣ Run
java Driver
🧪 Manual Test Cases
✔ Create customer (valid email)
✔ Create customer (invalid email)
✔ Add paid room
✔ Add free room
✔ Book room successfully
✔ Prevent double booking
✔ Search when no rooms available
✔ View reservations
✔ Admin view all data
