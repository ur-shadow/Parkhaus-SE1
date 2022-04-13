import java.util.UUID;

public interface IParkingGarage {
    Boolean markSpot(int PNr);

    Boolean reserveSpot(int PNr);

    int getNextSpot();

    int getNextHandyCapSpot();

    UUID createTicket(Boolean handyCap);

    UUID createTicket(int PNr);

    Boolean IsSpotOccupied(int PNr);

    int[] getFreeSpots();

    Boolean IsSpotReserved(int PNr);

    int getTicketSpot(UUID ticketId);

    Boolean IsHandyCapSpot(int PNr);
}
