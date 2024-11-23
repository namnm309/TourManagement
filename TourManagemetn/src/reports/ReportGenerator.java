package reports;

import java.util.HashMap;
import java.util.Map;
import controller.BookingController;
import model.Booking;

public class ReportGenerator {
    private BookingController bookingController;

    public ReportGenerator(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public void analyzePopularTours() {
        Map<String, Integer> tourPopularity = new HashMap<>();
        for (Booking booking : bookingController.getBookings()) {
            tourPopularity.put(booking.getTourId(), tourPopularity.getOrDefault(booking.getTourId(), 0) + 1);
        }
        System.out.println("Tour popularity: " + tourPopularity);
    }

    public void analyzeHotelOccupancy() {
        Map<String, Integer> hotelOccupancy = new HashMap<>();
        for (Booking booking : bookingController.getBookings()) {
            hotelOccupancy.put(booking.getHotelId(), hotelOccupancy.getOrDefault(booking.getHotelId(), 0) + 1);
        }
        System.out.println("Hotel occupancy: " + hotelOccupancy);
    }

    public void provideCustomerPreferences() {
        Map<String, Integer> customerPreferences = new HashMap<>();
        for (Booking booking : bookingController.getBookings()) {
            String tourAndHotel = booking.getTourId() + " " + booking.getHotelId();
            customerPreferences.put(tourAndHotel, customerPreferences.getOrDefault(tourAndHotel, 0) + 1);
        }
        System.out.println("Customer preferences: " + customerPreferences);
    }

    public void calculateRevenue() {
        double revenue = bookingController.getRevenue();
        System.out.println("Revenue: " + revenue);
    }

}
