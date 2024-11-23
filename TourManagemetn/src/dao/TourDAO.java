package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Tour;

public class TourDAO {
    public boolean save(ArrayList<Tour> tours) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tour.dat"))) {
            oos.writeObject(tours);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving tours: " + e.getMessage());
        }
        return false;
    }
}
