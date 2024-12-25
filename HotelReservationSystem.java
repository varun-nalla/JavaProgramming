import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ", ₹" + price + ") - " + (isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    private int reservationId;
    private String customerName;
    private Room room;
    private double totalPrice;

    public Reservation(int reservationId, String customerName, Room room) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
        this.totalPrice = room.getPrice();
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Customer: " + customerName + ", Room: " + room.getRoomNumber() + ", Total Price: ₹" + totalPrice;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static int nextReservationId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize rooms
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 300));
        rooms.add(new Room(104, "Single", 100));
        rooms.add(new Room(105, "Double", 150));

        int choice;

        do {
            System.out.println("\nHotel Reservation System:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;

                case 2:
                    makeReservation(sc);
                    break;

                case 3:
                    viewReservationDetails(sc);
                    break;

                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner sc) {
        System.out.print("Enter your name: ");
        sc.nextLine(); // Consume newline
        String customerName = sc.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = sc.nextInt();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            selectedRoom.bookRoom();
            Reservation reservation = new Reservation(nextReservationId++, customerName, selectedRoom);
            reservations.put(reservation.getReservationId(), reservation);
            System.out.println("Reservation successful! Your reservation details:");
            System.out.println(reservation);
        } else {
            System.out.println("Room not available or invalid room number.");
        }
    }

    private static void viewReservationDetails(Scanner sc) {
        System.out.print("Enter reservation ID: ");
        int reservationId = sc.nextInt();

        if (reservations.containsKey(reservationId)) {
            System.out.println(reservations.get(reservationId));
        } else {
            System.out.println("Reservation not found.");
        }
    }
}
