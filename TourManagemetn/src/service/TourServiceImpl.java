package service;

import java.util.ArrayList;

import controller.TourController;
import model.Tour;
import utils.Validation;
import view.Menu;

public class TourServiceImpl implements TourService {
    private TourController tourController;

    public TourServiceImpl(TourController tourController) {
        this.tourController = tourController;
    }

    public void createTour() {
        System.out.println("--Create tour--");
        String tourId = Validation.generateCode("T", 5, tourController.getTours().size());
        String name = Validation.readStr("Enter tour name", "[a-zA-Z0-9 ]+");
        String destination = Validation.readStr("Enter destination", "[a-zA-Z0-9 ]+");
        String description = Validation.readStr("Enter description", "[a-zA-Z0-9 ]+");
        double price = Validation.readDouble("Enter price", 1, Double.MAX_VALUE);
        String inclusions = Validation.readStr("Enter inclusions", "[a-zA-Z0-9 ]+");
        String exclusions = Validation.readStr("Enter exclusions", "[a-zA-Z0-9 ]+");
        int duration = Validation.readInteger("Enter duration", 1, Integer.MAX_VALUE);
        Tour newTour = new Tour(tourId, name, destination, duration, description, duration, inclusions, exclusions);
        if (tourController.createTour(newTour)) {
            System.out.println("Tour added successfully");
        } else {
            System.out.println("Tour added failed");
        }
    }

    public void searchAndUpdateTour() {
        System.out.println("--Search and update tour--");
        String destination = Validation.readStr("Enter destination ( Press enter to skip )", "[a-zA-Z0-9 ]*");
        int duration = Validation.readInteger("Enter duration: ( Press 0 to skip )", 0, Integer.MAX_VALUE);
        double price = Validation.readDouble("Enter price ( Press 0 to skip )", 0, Double.MAX_VALUE);
        ArrayList<Tour> tours = tourController.searchTours(destination, duration, price);
        if (tours == null || tours.size() == 0) {
            System.out.println("No tour found");
            return;
        }

        System.out.println("Found " + tours.size() + " tour(s)");

        Menu<Tour> menu = new Menu<Tour>();

        Tour tour = (Tour) menu.getObjectChoice(tours);
        if (tour == null) {
            System.out.println("No tour found");
            return;
        }
        String name = Validation.readStr("Enter tour new name", "[a-zA-Z0-9 ]+");
        String newDestination = Validation.readStr("Enter new destination", "[a-zA-Z0-9 ]+");
        String description = Validation.readStr("Enter new description", "[a-zA-Z0-9 ]+");
        String inclusions = Validation.readStr("Enter new inclusions", "[a-zA-Z0-9 ]+");
        String exclusions = Validation.readStr("Enter new exclusions", "[a-zA-Z0-9 ]+");
        int newDuration = Validation.readInteger("Enter new duration", 1, Integer.MAX_VALUE);
        double newPrice = Validation.readDouble("Enter new price", 1, Double.MAX_VALUE);
        Tour newTour = new Tour(tour.getTourId(), name, newDestination, newDuration, description, newPrice, inclusions,
                exclusions);
        if (tourController.updateTour(newTour)) {
            System.out.println("Tour updated successfully");
        } else {
            System.out.println("Tour updated failed");
        }
    }

    public void deleteTour() {
        System.out.println("--Delete tour--");
        String destination = Validation.readStr("Enter destination ( Press enter to skip )", "[a-zA-Z0-9 ]*");
        int duration = Validation.readInteger("Enter duration: ( Press 0 to skip )", 0, Integer.MAX_VALUE);
        double price = Validation.readDouble("Enter price ( Press 0 to skip )", 0, Double.MAX_VALUE);
        ArrayList<Tour> tours = tourController.searchTours(destination, duration, price);

        if (tours == null) {
            System.out.println("No tour found");
            return;
        }
        Menu<Tour> menu = new Menu<Tour>();
        Tour tour = (Tour) menu.getObjectChoice(tours);
        if (tour == null) {
            System.out.println("No tour found");
            return;
        }
        boolean agreed = Validation.readBoolean("Are you sure to delete this tour?");
        if (agreed) {
            if (tourController.deleteTour(tour.getTourId())) {
                System.out.println("Tour deleted successfully");
            } else {
                System.out.println("Tour deleted failed");
            }
        }
    }
}
