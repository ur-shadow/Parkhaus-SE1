import java.util.UUID;

public class ParkingSpot implements IParkingSpot {
    @Override
    public void markSpot(int PNr) {

    }

    @Override
    public void reserveSpot(int PNr) {

    }

    @Override
    public int getNextSpot() {
        return 0;
    }

    @Override
    public int getNextHSpot() {
        return 0;
    }

    @Override
    public UUID createTicket(int PNr) {
        return UUID.randomUUID();
    }

    @Override
    public Boolean[] getEmptySpots() {
        Boolean[] Spots = new Boolean[]{};
        return Spots;
    }
}
