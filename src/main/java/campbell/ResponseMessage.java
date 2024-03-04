package campbell;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
    private String status;
    // Add other fields as needed

    public ResponseMessage(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Add getters and setters for other fields
}

