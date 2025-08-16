package za.co.hotel;

import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Hotel hotel = new Hotel(3, 6, 1000.0); // 3 floors, 6 rooms each, base rate R1000

        while (true) {
            System.out.println("HOTEL MANAGER");
            System.out.println("==============");
            System.out.println("(1) Book a room");
            System.out.println("(2) Cancel a booking");
            System.out.println("(3) View report");
            System.out.println("(4) Exit");
            System.out.print("> ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1": {
                    int f = askInt(in, "Enter floor (1-" + hotel.getFloors() + "): ") - 1;
                    int r = askInt(in, "Enter room on floor (1-" + hotel.getRoomsPerFloor() + "): ") - 1;
                    boolean ok = hotel.book(f, r);
                    System.out.println(ok ? "Booking successful." : "Room already occupied.");
                    break;
                }
                case "2": {
                    int f = askInt(in, "Enter floor (1-" + hotel.getFloors() + "): ") - 1;
                    int r = askInt(in, "Enter room on floor (1-" + hotel.getRoomsPerFloor() + "): ") - 1;
                    boolean ok = hotel.cancel(f, r);
                    System.out.println(ok ? "Cancellation successful." : "Room was not booked.");
                    break;
                }
                case "3": hotel.printReport(); break;
                case "4": System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static int askInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (s.matches("\\d+")) return Integer.parseInt(s);
            System.out.println("Please enter a number.");
        }
    }
}
