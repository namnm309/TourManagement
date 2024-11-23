package controller;

import java.util.ArrayList;

import model.Tour;
import dao.TourDAO;

public class TourController {
    private ArrayList<Tour> tours;
    private TourDAO tourDAO;

    public TourController() {
        tours = new ArrayList<>();
        tourDAO = new TourDAO();
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public void displayTours(ArrayList<Tour> tours) {
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }

    public boolean createTour(Tour tour) {
        return tours.add(tour);
    }

    public boolean updateTour(Tour tour) {
        Tour foundTour = searchTour(tour.getTourId());
        if (foundTour != null) {
            foundTour.setName(tour.getName());
            foundTour.setDestination(tour.getDestination());
            foundTour.setDuration(tour.getDuration());
            foundTour.setDescription(tour.getDescription());
            foundTour.setPrice(tour.getPrice());
            foundTour.setInclusions(tour.getInclusions());
            foundTour.setExclusions(tour.getExclusions());
            return true;
        }
        return false;
    }

    public boolean deleteTour(String id) {
        Tour foundTour = searchTour(id);
        if (foundTour != null) {
            tours.remove(foundTour);
            return true;
        }
        return false;
    }

    public ArrayList<Tour> searchToursByDestination(String destination) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDestination().equalsIgnoreCase(destination)) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchToursByDuration(int duration) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDuration() == duration) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchToursByPrice(double price) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getPrice() == price) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchToursByDurationAndPrice(int duration, double price) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDuration() == duration && tour.getPrice() == price) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchToursByDestinationAndDuration(String destination, int duration) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDestination().equalsIgnoreCase(destination) && tour.getDuration() == duration) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchTourByDestinationDurationAndPrice(String destination, int duration, double price) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDestination().equalsIgnoreCase(destination) && tour.getDuration() == duration
                    && tour.getPrice() == price) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchToursByDestinationAndPrice(String destination, double price) {
        ArrayList<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getDestination().equalsIgnoreCase(destination) && tour.getPrice() == price) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }

    public ArrayList<Tour> searchTours(String destination, int duration, double price) {
        int flag = 0;
        if (destination.trim().length() > 0)
            flag++;
        if (duration > 0)
            flag += 2;
        if (price > 0)
            flag += 4;
        switch (flag) {
            case 0:
                return null;
            case 1:
                return searchToursByDestination(destination);
            case 2:
                return searchToursByDuration(duration);
            case 3:
                return searchToursByDestinationAndDuration(destination, duration);
            case 4:
                return searchToursByPrice(price);
            case 5:
                return searchToursByDestinationAndPrice(destination, price);
            case 6:
                return searchToursByDurationAndPrice(duration, price);
            case 7:
                return searchTourByDestinationDurationAndPrice(destination, duration, price);
        }
        return null;
    }

    public Tour searchTour(String id) {
        for (Tour tour : tours) {
            if (tour.getTourId().equals(id)) {
                return tour;
            }
        }
        return null;
    }

    public boolean saveTours() {
        return tourDAO.save(tours);
    }
}
