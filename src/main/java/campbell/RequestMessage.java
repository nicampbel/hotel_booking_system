package campbell;

import java.io.Serializable;

public class RequestMessage implements Serializable {
    private String type;
    private String roomNumber; // New field for room number
    // Add other fields as needed

    public RequestMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getRoomNumber() {
    return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
    }

    // Add getters and setters for other fields
}

