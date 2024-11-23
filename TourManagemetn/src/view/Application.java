package view;

import java.util.ArrayList;

import controller.BookingController;
import controller.HotelController;
import controller.TourController;
import reports.ReportGenerator;
import service.BookingService;
import service.BookingServiceImpl;
import service.HotelService;
import service.HotelServiceImpl;
import service.TourService;
import service.TourServiceImpl;

public class Application {
    private TourController tourController;
    private HotelController hotelController;
    private BookingController bookingController;
    private TourService tourService;
    private HotelService hotelService;
    private BookingService bookingService;
    private ReportGenerator reportGenerator;
    private Menu<String> menu;

    public Application() {
        tourController = new TourController();
        hotelController = new HotelController();
        bookingController = new BookingController(tourController, hotelController);
        tourService = new TourServiceImpl(tourController);
        hotelService = new HotelServiceImpl(hotelController);
        bookingService = new BookingServiceImpl(bookingController);
        menu = new Menu<String>();
    }

    public void excute() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Manage Tour");
        list.add("Manage Hotels");
        list.add("Manage Bookings");
        list.add("Genarate Reports");
        list.add("Save data into dat file");
        list.add("Exit");
        while (true) {
            int choice = menu.getIntChoice(list);
            switch (choice) {
                case 1:
                    manageTour();
                    break;
                case 2:
                    manageHotels();
                    break;
                case 3:
                    manageBookings();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    saveData();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

    }

    public void manageTour() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Create Tour");
        list.add("Search and Update Tour");
        list.add("Delete Tour");
        list.add("Back main menu");
        while (true) {
            int choice = menu.getIntChoice(list);
            switch (choice) {
                case 1:
                    tourService.createTour();
                    break;
                case 2:
                    tourService.searchAndUpdateTour();
                    break;
                case 3:
                    tourService.deleteTour();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        }

    }

    public void manageHotels() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Create Hotel");
        list.add("Search and Update Hotel");
        list.add("Delete Hotel");
        list.add("Back main menu");
        while (true) {
            int choice = menu.getIntChoice(list);
            switch (choice) {
                case 1:
                    hotelService.createHotel();
                    break;
                case 2:
                    hotelService.searchAndUpdateHotel();
                    break;
                case 3:
                    hotelService.deleteHotel();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        }
    }

    public void manageBookings() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Customer Booking");
        list.add("View booked and manage booking");
        list.add("Back main menu");
        while (true) {
            int choice = menu.getIntChoice(list);
            switch (choice) {
                case 1:
                    bookingService.booking();
                    break;
                case 2:
                    bookingService.viewAndManageBooking();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        }
    }

    public void generateReports() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Analyze popular tours");
        list.add("Analyze hotel occupancy");
        list.add("Provide customer preferences");
        list.add("Calculate revenue");
        list.add("Back main menu");
        while (true) {
            int choice = menu.getIntChoice(list);
            switch (choice) {
                case 1:
                    reportGenerator.analyzePopularTours();
                    break;
                case 2:
                    reportGenerator.analyzeHotelOccupancy();
                    break;
                case 3:
                    reportGenerator.provideCustomerPreferences();
                    break;
                case 4:
                    reportGenerator.calculateRevenue();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        }
    }

    public void saveData() {

        String message1 = tourController.saveTours() ? "Save tours successfully" : "Save tours failed";
        System.out.println(message1);
        String message2 = hotelController.saveHotels() ? "Save hotels successfully" : "Save hotels failed";

        System.out.println(message2);
    }

}
