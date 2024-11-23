package service;

import java.util.ArrayList;
import java.util.List;

import controller.HotelController;
import model.Hotel;
import utils.Validation;
import view.Menu;

public class HotelServiceImpl implements HotelService {
    private HotelController hotelController;

    public HotelServiceImpl(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    @Override
    public void createHotel() {
        System.out.println("--Create hotel--");
        String hotelId = Validation.generateCode("H", 5, hotelController.getHotels().size());
        String name = Validation.readStr("Enter hotel name", "[a-zA-Z0-9 ]+");
        String location = Validation.readStr("Enter location", "[a-zA-Z0-9 ]+");
        int availableRooms = Validation.readInteger("Enter available rooms", 1, Integer.MAX_VALUE);
        double pricing = Validation.readDouble("Enter pricing", 1, Double.MAX_VALUE);
        List<String> amenities = Validation.readList("Enter amenities (separated by comma)", "[a-zA-Z0-9 ]+");
        Hotel newHotel = new Hotel(hotelId, name, location, availableRooms, amenities, pricing);
        if (hotelController.addHotel(newHotel)) {
            System.out.println("Hotel added successfully");

        } else {
            System.out.println("Hotel added failed");
        }
    }

    @Override
    public void searchAndUpdateHotel() {
        System.out.println("--Search and update hotel--");
        Hotel hotel = searchHotel();
        if (hotel == null) {
            return;
        } else {
            int availableRooms = Validation.readInteger("Enter new available rooms", 1, Integer.MAX_VALUE);
            double pricing = Validation.readDouble("Enter new pricing", 1, Double.MAX_VALUE);
            List<String> amenities = Validation.readList("Enter new amenities (separated by comma)", "[a-zA-Z0-9 ]+");
            hotel.setAvailableRooms(availableRooms);
            hotel.setAmenities(amenities);
            hotel.setPricing(pricing);
            if (hotelController.updateHotel(hotel)) {
                System.out.println("Hotel updated successfully.");
            } else {
                System.out.println("Hotel updated failed.");
            }
        }
    }

    @Override
    public void deleteHotel() {
        System.out.println("--Delete hotel--");
        Hotel hotel = searchHotel();
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        } else {
            boolean agree = Validation.readBoolean("Are you sure to delete this hotel? (Y/N)");
            if (!agree) {
                return;
            }
            if (hotelController.deleteHotel(hotel)) {
                System.out.println("Hotel deleted successfully.");

            } else {
                System.out.println("Hotel deleted failed.");

            }
        }
        return;
    }

    public Hotel searchHotel() {

        String searchCriteria = Validation.readStr("Enter search criteria (location, amenities, or room availability)",
                "[a-zA-Z0-9 ]+");
        ArrayList<Hotel> hotelsFounded;
        // Search by location
        if (searchCriteria.equalsIgnoreCase("location")) {
            String location = Validation.readStr("Enter location to search", "[a-zA-Z0-9 ]+");
            hotelsFounded = hotelController.searchHotelsByLocation(location);
        }

        // Search by amenities
        else if (searchCriteria.equalsIgnoreCase("amenities")) {
            String amenities = Validation.readStr("Enter amenities to search (separated by comma)", ",");
            hotelsFounded = hotelController.searchHotelsByAmenities(amenities);
        }

        // Search by room availability
        else if (searchCriteria.equalsIgnoreCase("room availability")) {
            int availableRooms = Validation.readInteger("Enter minimum number of available rooms", 1,
                    Integer.MAX_VALUE);
            hotelsFounded = hotelController.searchHotelsByRoomAvailability(availableRooms);
        }
        // Invalid search criteria
        else {
            System.out.println("Invalid search criteria. Please try again.");
            return null;
        }
        // Update hotel
        if (hotelsFounded.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            Menu<Hotel> menu = new Menu<Hotel>();
            Hotel hotel = (Hotel) menu.getObjectChoice(hotelsFounded);
            if (hotel == null) {
                System.out.println("No hotel found.");
            } else {
                return hotel;
            }
        }
        return null;
    }

}
