/*
 * ╔════════════════════════════════════════════════════╗
 * ║              File: RequestMessage.java             ║
 * ║  Description: Request message class                ║
 * ║  Author: NE Campbell, 2024                         ║
 * ╚════════════════════════════════════════════════════╝
 */

package campbell;

import java.io.Serializable;

public class RequestMessage implements Serializable {
    private String type;
    private String roomNumber;

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
}

