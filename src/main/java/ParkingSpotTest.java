import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.UUID;

class ParkingSpotTest {

    IParkingGarage ps;

    @BeforeEach
    void testStartUp() {
        ps = new ParkingGarage(100, 21);
    }

    @Test
    void testMarkSpot() {
        assertTrue(ps.markSpot(5));
        assertTrue(ps.IsSpotOccupied(5));
        assertFalse(ps.markSpot(5));
        assertFalse(ps.IsSpotOccupied(6));
    }

    @Test
    void testReserveSpot() {
        assertTrue(ps.reserveSpot(5));
        assertTrue(ps.IsSpotReserved(5));
        assertFalse(ps.reserveSpot(5));
        assertFalse(ps.IsSpotReserved(6));
    }

    @Test
    void testGetNextSpot() {
        assertEquals(0, ps.getNextSpot());
        assertTrue(ps.markSpot(0));
        assertEquals(1, ps.getNextSpot());
        assertTrue(ps.markSpot(1));
        assertEquals(2, ps.getNextSpot());
        assertTrue(ps.markSpot(2));
        assertEquals(3, ps.getNextSpot());
        assertTrue(ps.markSpot(4));
        assertEquals(3, ps.getNextSpot());
    }

    @Test
    void testGetNextHandyCapSpot() {
        assertEquals(80, ps.getNextHandyCapSpot());
        ps.markSpot(80);
        assertEquals(81, ps.getNextHandyCapSpot());
        ps.markSpot(81);
        assertEquals(82, ps.getNextHandyCapSpot());
        ps.markSpot(82);
    }

    @Test
    void testCreateTicket() {
        UUID T1 = ps.createTicket(1);
        UUID T2 = ps.createTicket(2);
        UUID T3 = ps.createTicket(6);
        UUID T4 = ps.createTicket(true);
        UUID T5 = ps.createTicket(false);
        assertEquals(80, ps.getTicketSpot(T4));
        assertEquals(0, ps.getTicketSpot(T5));
        assertTrue(ps.IsHandyCapSpot(ps.getTicketSpot(T4)));
        assertFalse(ps.IsHandyCapSpot(ps.getTicketSpot(T5)));
        assertNotEquals(T1, T2);
        assertNotEquals(T1, T3);
        assertNotEquals(T3, T2);
    }

    @Test
    void testGetFreeSpots() {
        assertTrue(ps.markSpot(5));
        assertTrue(ps.markSpot(6));
        int[] freeSpots = ps.getFreeSpots();
        for (int i = 0; i < freeSpots.length; i++) {
            assertNotEquals(5, freeSpots[i]);
            assertNotEquals(6, freeSpots[i]);
        }
    }
}