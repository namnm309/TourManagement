package controller;

import java.util.ArrayList;
import java.util.List;
import dao.HotelDAO;
import model.Hotel;

public class HotelController {
    private ArrayList<Hotel> hotels;
    private HotelDAO hotelDAO;

    public HotelController() {
        hotels = new ArrayList<>();
        hotelDAO = new HotelDAO();

    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public boolean addHotel(Hotel hotel) {
        if (!hotels.contains(hotel)) {
            return hotels.add(hotel);
        } else {
            return false;
        }
    }

    public ArrayList<Hotel> searchHotelsByLocation(String location) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getLocation().equalsIgnoreCase(location)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public ArrayList<Hotel> searchHotelsByAmenities(String amenities) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getAmenities().contains(amenities)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public ArrayList<Hotel> searchHotelsByRoomAvailability(int availableRooms) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getAvailableRooms() >= availableRooms) {
                result.add(hotel);
            }
        }
        return result;
    }

    public boolean updateHotel(Hotel hotel) {
        Hotel foundHotel = searchHotel(hotel.getHotelId());
        if (foundHotel == null) {
            return false;
        }
        foundHotel.setAvailableRooms(hotel.getAvailableRooms());
        foundHotel.setAmenities(hotel.getAmenities());
        foundHotel.setPricing(hotel.getPricing());

        return true;
    }

    public boolean deleteHotel(Hotel hotel) {
        return hotels.remove(hotel);
    }

    public Hotel searchHotel(String id) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId().equalsIgnoreCase(id)) {
                return hotel;
            }
        }
        return null;
    }

    public boolean saveHotels() {
        return hotelDAO.save(hotels);
    }

}
