package model;

import java.util.List;

public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private int availableRooms;
    private List<String> amenities;
    private double pricing;

    // Constructor
    public Hotel(String hotelId, String name, String location, int availableRooms, List<String> amenities,
            double pricing) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.availableRooms = availableRooms;
        this.amenities = amenities;
        this.pricing = pricing;
    }

    // Getters and setters
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    // toString method
    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", availableRooms=" + availableRooms +
                ", amenities=" + amenities +
                ", pricing=" + pricing +
                '}';
    }

}
