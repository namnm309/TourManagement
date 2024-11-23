package model;

public class Tour {
    private String tourId;
    private String name;
    private String destination;
    private int duration;
    private String description;
    private double price;
    private String inclusions;
    private String exclusions;

    // Constructor
    public Tour(String tourId, String name, String destination, int duration, String description, double price,
            String inclusions, String exclusions) {
        this.tourId = tourId;
        this.name = name;
        this.destination = destination;
        this.duration = duration;
        this.description = description;
        this.price = price;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
    }

    // Getters and Setters
    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    // toString method
    @Override
    public String toString() {
        return "Tour ID: " + tourId +
                " | Name: " + name +
                " | Destination: " + destination +
                " | Duration: " + duration +
                " | Description: " + description +
                " | Price: " + price +
                " | Inclusions: " + inclusions +
                " | Exclusions: " + exclusions;
    }
}
