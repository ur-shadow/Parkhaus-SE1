import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.UUID;

class ParkingSpotTest {

    IParkingSpot ps;

    @BeforeEach
    void startUp() {
        ps = new ParkingSpot();
    }

    @Test
    void markSpot() {
        ps.markSpot(5);
        assertFalse(ps.getEmptySpots()[5]);
    }

    @Test
    void reserveSpot() {
        ps.reserveSpot(3);
        assertFalse(ps.getEmptySpots()[3]);
    }

    @Test
    void getNextSpot() {
        assertEquals(0, ps.getNextSpot());
        ps.markSpot(0);
        assertEquals(1, ps.getNextSpot());
        ps.markSpot(1);
        assertEquals(2, ps.getNextSpot());
        ps.markSpot(2);
    }

    @Test
    void getNextHSpot() {
        assertEquals(40, ps.getNextHSpot());
        ps.markSpot(40);
        assertEquals(41, ps.getNextHSpot());
        ps.markSpot(41);
        assertEquals(42, ps.getNextHSpot());
        ps.markSpot(42);
    }

    @Test
    void createTicket() {
        UUID T1 = ps.createTicket(1);
        UUID T2 = ps.createTicket(2);
        UUID T3 = ps.createTicket(6);

        assertNotEquals(T1,T2);
        assertNotEquals(T1,T3);
        assertNotEquals(T3,T2);

    }
}