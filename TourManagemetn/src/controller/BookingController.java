package controller;

import java.util.Date;
import java.util.ArrayList;

import model.Booking;
import model.Customer;
import utils.Validation;

public class BookingController {
    private ArrayList<Booking> bookings;
    private ArrayList<Customer> customers;
    private TourController tourController;
    private HotelController hotelController;

    public BookingController(TourController tourController, HotelController hotelController) {
        bookings = new ArrayList<>();
        customers = new ArrayList<>();
        this.tourController = tourController;
        this.hotelController = hotelController;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean addCustomer(Customer c) {
        if (!customers.contains(c)) {
            return customers.add(c);
        } else {
            return false;
        }
    }

    public boolean bookTourAndHotel(Customer c, String tourId, String hotelId, int numberOfPeople, Date date,
            int numberOfRooms) {
        if (validateBooking(tourId, hotelId, numberOfPeople, date, numberOfRooms)) {
            addCustomer(c);
            Booking booking = new Booking();
            booking.setId(Validation.generateCode("B", 7, bookings.size()));
            booking.setCustomerId(c.getId());
            booking.setTourId(tourId);
            booking.setHotelId(hotelId);
            booking.setNumberOfRooms(numberOfRooms);
            hotelController.searchHotel(hotelId).setAvailableRooms(
                    hotelController.searchHotel(hotelId).getAvailableRooms() - numberOfRooms);
            booking.setNumberOfPeople(numberOfPeople);
            booking.setDate(date);
            return bookings.add(booking);
        } else {
            return false;
        }
    }

    public boolean validateBooking(String tourId, String hotelId, int numberOfPeople, Date date, int numberOfRooms) {
        if (tourController.searchTour(tourId) == null) {
            System.out.println("Tour not found");
            return false;
        }
        if (hotelController.searchHotel(hotelId) == null) {
            System.out.println("Hotel not found");
            return false;
        }
        if (numberOfPeople <= 0) {
            System.out.println("Number of people must be greater than 0");
            return false;
        }
        if (numberOfRooms <= 0) {
            System.out.println("Number of rooms must be greater than 0");
            return false;
        }
        if (date.before(new Date())) {
            System.out.println("Date must be greater than current date");
            return false;
        }
        return true;
    }

    public ArrayList<Booking> getBookingsByCustomerId(String customerId) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomerId().equals(customerId)) {
                result.add(booking);
            }
        }
        return result;
    }

    public boolean modifyBooking(String bookingId, String tourId, String hotelId, int numberOfPeople, Date date,
            int numberOfRooms) {
        Booking booking = searchBooking(bookingId);
        if (booking != null) {
            if (validateBooking(tourId, hotelId, numberOfPeople, date, numberOfRooms)) {
                booking.setTourId(tourId);
                booking.setHotelId(hotelId);
                booking.setNumberOfPeople(numberOfPeople);
                booking.setDate(date);
                hotelController.searchHotel(hotelId).setAvailableRooms(
                        hotelController.searchHotel(hotelId).getAvailableRooms() - numberOfRooms
                                + booking.getNumberOfRooms());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = searchBooking(bookingId);
        if (booking != null) {
            bookings.remove(booking);
            customers.remove(searchCustomer(booking.getCustomerId()));
            hotelController.searchHotel(booking.getHotelId()).setAvailableRooms(
                    hotelController.searchHotel(booking.getHotelId()).getAvailableRooms() + booking.getNumberOfRooms());
            return true;
        } else {
            return false;
        }
    }

    public Booking searchBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    public Customer searchCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;

    }

    public double getRevenue() {
        double revenue = 0;
        for (Booking booking : bookings) {
            revenue += tourController.searchTour(booking.getTourId()).getPrice() * booking.getNumberOfPeople();
        }
        return revenue;
    }
}