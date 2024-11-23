package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Hotel;

public class HotelDAO {
    // save hotels to file
    public boolean save(ArrayList<Hotel> hotels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hotel.dat"))) {
            oos.writeObject(hotels);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving hotels: " + e.getMessage());
        }
        return false;
    }
}
