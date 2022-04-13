import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class ParkingGarage implements IParkingGarage {
    class ParkingSpot {
        public Boolean IsHandyCapSpot;
        public Boolean Occupied;
        public Boolean Reserved;

        public ParkingSpot() {
            IsHandyCapSpot = false;
            Occupied = false;
            Reserved = false;
        }

        public ParkingSpot(Boolean isHandyCapSpot, Boolean occupied, Boolean reservations) {
            IsHandyCapSpot = isHandyCapSpot;
            Occupied = occupied;
            Reserved = reservations;
        }
    }

    ParkingSpot[] Spots;
    HashMap<UUID, Integer> ticketMap;

    public ParkingGarage(int nrSpots, int nrHandyCapSpots) {
        if (nrHandyCapSpots > nrSpots)
            throw new IllegalArgumentException("nrHandyCapSpots cant be greater than nrSpots");
        ticketMap = new HashMap<>();
        Spots = new ParkingSpot[nrSpots];
        for (int i = 0; i < nrSpots; i++) {
            Spots[i] = new ParkingSpot();
            if (i > nrSpots - nrHandyCapSpots) {
                Spots[i].IsHandyCapSpot = true;
            }
        }
    }

    @Override
    public Boolean markSpot(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            return false;
        if (!Spots[PNr].Occupied) {
            Spots[PNr].Occupied = true;
            return true;
        }
        return false;
    }

    @Override
    public Boolean reserveSpot(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            return false;
        if (!Spots[PNr].Reserved) {
            Spots[PNr].Reserved = true;
            return true;
        }
        return false;
    }

    @Override
    public int getNextSpot() {
        for (int i = 0; i < Spots.length; i++) {
            if (!IsSpotOccupied(i) && !IsSpotReserved(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getNextHandyCapSpot() {
        for (int i = 0; i < Spots.length; i++) {
            if (!IsSpotOccupied(i) && !IsSpotReserved(i) && Spots[i].IsHandyCapSpot) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public UUID createTicket(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            throw new IndexOutOfBoundsException();
        if (!IsSpotOccupied(PNr) && !IsSpotReserved(PNr)) {
            reserveSpot(PNr);
            UUID id = UUID.randomUUID();
            ticketMap.put(id, PNr);
            return id;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public UUID createTicket(Boolean handyCap) {
        int spot;
        if (handyCap)
            spot = getNextHandyCapSpot();
        else
            spot = getNextSpot();

        return createTicket(spot);
    }

    @Override
    public Boolean IsHandyCapSpot(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            return false;
        return Spots[PNr].IsHandyCapSpot;
    }

    @Override
    public Boolean IsSpotReserved(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            return false;
        return Spots[PNr].Reserved;
    }

    @Override
    public Boolean IsSpotOccupied(int PNr) {
        if (PNr < 0 || PNr >= Spots.length)
            return false;
        return Spots[PNr].Occupied;
    }

    @Override
    public int[] getFreeSpots() {
        int[] arr = new int[Spots.length];
        int index = 0;
        for (int i = 0; i < Spots.length; i++) {
            if (!IsSpotOccupied(i) && !IsSpotReserved(i))
                arr[index++] = i;
        }
        return Arrays.copyOfRange(arr, 0, index);
    }

    @Override
    public int getTicketSpot(UUID ticketId) {

        Integer parkSpot = ticketMap.get(ticketId);
        if (parkSpot != null)
            return parkSpot;
        return -1;
    }
}
