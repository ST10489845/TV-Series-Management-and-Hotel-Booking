package za.co.hotel;

import java.util.Arrays;

public class Hotel {
    private final Room[][] rooms;        // floors x roomsPerFloor
    private final boolean[][] occupied;  // same shape as rooms
    private final double baseRate;

    // floors >=1, roomsPerFloor >=1
    public Hotel(int floors, int roomsPerFloor, double baseRate) {
        this.baseRate = baseRate;
        this.rooms = new Room[floors][roomsPerFloor];
        this.occupied = new boolean[floors][roomsPerFloor];

        // Build hotel: even rooms are Standard, odd are Deluxe (just to vary inheritance)
        for (int f = 0; f < floors; f++) {
            for (int r = 0; r < roomsPerFloor; r++) {
                String id = (f+1) + "-" + String.format("%02d", r+1);
                rooms[f][r] = (r % 2 == 0)
                    ? new StandardRoom(id, baseRate)
                    : new DeluxeRoom(id, baseRate);
            }
        }
    }

    // Information hiding: expose read-only copies where relevant
    public int getFloors() { return rooms.length; }
    public int getRoomsPerFloor() { return rooms[0].length; }

    public Room getRoom(int floor, int room) { return rooms[floor][room]; }
    public boolean isOccupied(int floor, int room) { return occupied[floor][room]; }

    // Book if free; return true if successful
    public boolean book(int floor, int room) {
        if (occupied[floor][room]) return false;
        occupied[floor][room] = true;
        return true;
    }

    // Cancel if booked; return true if successful
    public boolean cancel(int floor, int room) {
        if (!occupied[floor][room]) return false;
        occupied[floor][room] = false;
        return true;
    }

    // Compute potential nightly revenue from currently occupied rooms
    public double currentRevenue() {
        double sum = 0.0;
        for (int f = 0; f < rooms.length; f++) {
            for (int r = 0; r < rooms[f].length; r++) {
                if (occupied[f][r]) sum += rooms[f][r].getNightlyRate();
            }
        }
        return sum;
    }

    // Console report
    public void printReport() {
        System.out.println("\nHOTEL OCCUPANCY REPORT");
        System.out.println("=======================");
        int total = 0, used = 0;
        for (int f = 0; f < rooms.length; f++) {
            System.out.println("Floor " + (f+1) + ":");
            for (int r = 0; r < rooms[f].length; r++) {
                total++;
                boolean occ = occupied[f][r];
                if (occ) used++;
                Room room = rooms[f][r];
                System.out.println("  Room " + room.getId() +
                        "  Type: " + room.getClass().getSimpleName() +
                        "  Rate: " + String.format("%.2f", room.getNightlyRate()) +
                        "  Status: " + (occ ? "Occupied" : "Free"));
            }
        }
        double pct = (total == 0) ? 0 : (used * 100.0 / total);
        System.out.println("-----------------------");
        System.out.println("Total rooms: " + total);
        System.out.println("Occupied: " + used + " (" + String.format("%.1f", pct) + "%)");
        System.out.println("Current nightly revenue: " + String.format("%.2f", currentRevenue()));
        System.out.println("=======================\n");
    }
}
