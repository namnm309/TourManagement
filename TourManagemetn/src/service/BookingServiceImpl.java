package service;

import java.util.ArrayList;
import java.util.Date;
import controller.BookingController;
import model.Booking;
import model.Customer;
import utils.Validation;
import view.Menu;

public class BookingServiceImpl implements BookingService {
    BookingController bookingController;

    public BookingServiceImpl(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    @Override
    public void booking() {
        System.out.println("--Booking--");
        String name = Validation.readStr("Enter name", "[a-zA-Z ]+");
        String phone = Validation.readStr("Enter phone", "[0-9]{10,11}");
        String email = Validation.readStr("Enter email", "[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+");
        String customerId = Validation.generateCode("C", 5, bookingController.getCustomers().size());
        Customer newCustomer = new Customer(customerId, name, phone, email);
        String tourId = Validation.readStr("Enter tour id", "[a-zA-Z0-9]{6}");
        String hotelId = Validation.readStr("Enter hotel id", "[a-zA-Z0-9]{6}");
        int numberOfPeople = Validation.readInteger("Enter number of people", 1, Integer.MAX_VALUE);
        int numberOfRooms = Validation.readInteger("Enter number of room", 1, Integer.MAX_VALUE);
        Date date = Validation.readDate("Enter date", "dd/MM/yyyy");
        if (bookingController.bookTourAndHotel(newCustomer, tourId, hotelId, numberOfPeople, date, numberOfRooms)) {
            System.out.println("Customer booking successfully");
            System.out.println("Customer booked: " + newCustomer);
        } else {
            System.out.println("Customer booking failed");
        }
    }

    @Override
    public void viewAndManageBooking() {
        System.out.println("--View and manage booking--");
        String customerId = Validation.readStr("Enter customer id:", "[a-zA-Z0-9]{6}");

        ArrayList<Booking> bookings = bookingController.getBookingsByCustomerId(customerId);
        if (bookings == null || bookings.size() == 0) {
            System.out.println("No booking found");
            return;
        }
        System.out.println("Found " + bookings.size() + " booking(s)");
        Menu<Booking> menu = new Menu<Booking>();
        Booking booking = (Booking) menu.getObjectChoice(bookings);
        if (booking == null) {
            System.out.println("No booking found");
            return;
        }
        System.out.println("1. Modify booking");
        System.out.println("2. Cancle booking");
        int choice = Validation.readInteger("Enter your choice:", 1, 2);
        switch (choice) {
            case 1:
                String tourId = Validation.readStr("Enter new tour id", "[a-zA-Z0-9]{6}");
                String hotelId = Validation.readStr("Enter new hotel id", "[a-zA-Z0-9]{6}");
                int numberOfPeople = Validation.readInteger("Enter new number of people", 1, Integer.MAX_VALUE);
                int numberOfRooms = Validation.readInteger("Enter new number of room", 1, Integer.MAX_VALUE);
                Date date = Validation.readDate("Enter new date", "dd/MM/yyyy");
                boolean status = bookingController.modifyBooking(customerId, tourId, hotelId, numberOfPeople, date,
                        numberOfRooms);
                if (status) {
                    System.out.println("Booking modified successfully");
                } else {
                    System.out.println("Booking modified failed");
                }
                break;
            case 2:
                boolean status2 = bookingController.cancelBooking(booking.getId());
                if (status2) {
                    System.out.println("Booking canceled successfully");
                } else {
                    System.out.println("Booking canceled failed");
                }
                break;
            default:
                break;
        }
    }

}
