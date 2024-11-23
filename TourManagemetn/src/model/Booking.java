package model;

import java.util.Date;

public class Booking {
    private String id;
    private String customerId;
    private String tourId;
    private String hotelId;
    private int numberOfPeople;
    private int numberOfRooms;
    private Date date;

    // Constructor
    public Booking() {

    }

    public Booking(String id, String customerId, String tourId, String hotelId, int numberOfPeople, Date date,
            int numberOfRooms) {
        this.id = id;
        this.customerId = customerId;
        this.tourId = tourId;
        this.hotelId = hotelId;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.numberOfRooms = numberOfRooms;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    // toString method
    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", tourId='" + tourId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", numberOfPeople=" + numberOfPeople +
                ", date=" + date +
                '}';
    }
}
