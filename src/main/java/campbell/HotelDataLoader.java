package campbell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class HotelDataLoader {

    public static Hotel loadHotelData() {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/hotel_data.json"))) {
            Hotel hotel = gson.fromJson(reader, Hotel.class);
            System.out.println("Hotel data loaded successfully.");
            return hotel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
