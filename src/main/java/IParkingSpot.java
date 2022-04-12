import java.util.UUID;

public interface IParkingSpot {
    void markSpot(int PNr);

    void reserveSpot(int PNr);

    int getNextSpot();

    int getNextHSpot();

    UUID createTicket(int PNr);

    Boolean[] getEmptySpots();
}
