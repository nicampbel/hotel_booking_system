package campbell;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Floor> floors;

    public Hotel() {
        this.floors = new ArrayList<>();
        initializeFloors();
    }

    private void initializeFloors() {
        for (int i = 1; i <= 4; i++) {
            floors.add(new Floor(i));
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Room getRoom(String roomNumber) {
        for (Floor floor : floors) {
            Room room = floor.getRoom(roomNumber);
            if (room != null) {
                return room;
            }
        }
        return null;
    }
}
