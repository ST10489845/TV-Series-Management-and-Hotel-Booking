package za.co.hotel.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import za.co.hotel.Hotel;
import za.co.hotel.Room;

public class HotelTests {

    @Test
    void bookingAndRevenueFlow() {
        Hotel hotel = new Hotel(2, 4, 1000.0);

        // Book two rooms: (floor 0, room 0) -> Standard (rate 1000)
        // and (floor 0, room 1) -> Deluxe (rate 1500)
        assertTrue(hotel.book(0, 0));
        assertTrue(hotel.book(0, 1));

        // Double-book prevention
        assertFalse(hotel.book(0, 0));

        // Revenue check: 1000 + 1500 = 2500
        assertEquals(2500.0, hotel.currentRevenue(), 0.001);

        // Cancel one booking
        assertTrue(hotel.cancel(0, 1));
        assertFalse(hotel.cancel(0, 1)); // already cancelled

        // Revenue now only 1000
        assertEquals(1000.0, hotel.currentRevenue(), 0.001);
    }

    @Test
    void roomTypesAlternateByColumn() {
        Hotel hotel = new Hotel(1, 4, 1000.0);
        Room r0 = hotel.getRoom(0, 0);
        Room r1 = hotel.getRoom(0, 1);
        Room r2 = hotel.getRoom(0, 2);
        Room r3 = hotel.getRoom(0, 3);

        assertEquals("StandardRoom", r0.getClass().getSimpleName());
        assertEquals("DeluxeRoom",   r1.getClass().getSimpleName());
        assertEquals("StandardRoom", r2.getClass().getSimpleName());
        assertEquals("DeluxeRoom",   r3.getClass().getSimpleName());

        // Rates: standard = base, deluxe = base*1.5
        assertEquals(1000.0, r0.getNightlyRate(), 0.001);
        assertEquals(1500.0, r1.getNightlyRate(), 0.001);
    }
}
