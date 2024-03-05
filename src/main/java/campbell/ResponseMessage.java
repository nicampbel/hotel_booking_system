package campbell;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
    private String status;

    public ResponseMessage(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

