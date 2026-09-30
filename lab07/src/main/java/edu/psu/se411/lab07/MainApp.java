package edu.psu.se411.lab07;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.model.Booking;
import edu.psu.se411.lab07.model.CarRentalBooking;
import edu.psu.se411.lab07.model.FlightBooking;
import edu.psu.se411.lab07.model.TrainBooking;
import edu.psu.se411.lab07.util.SeatClass;

public class MainApp {

    static Logger logger = LoggerFactory.getLogger(MainApp.class);

    // Works for ANY booking type (polymorphism)
    public static double computeTotalPrice(Booking booking) {
        return booking.calculateTotalPrice();
    }

    public static void main(String[] args) {
        logger.info("Application is starting...");

        try {
            List<Booking> bookings = new ArrayList<>();

            // Valid bookings
            bookings.add(new FlightBooking("B001", "John Doe", new Date(), "New York", 200.0, 30.0, 20.0));
            bookings.add(new CarRentalBooking("B002", "Jane Smith", new Date(), "Los Angeles", 50.0, 10));
            bookings.add(new TrainBooking("B003", "Alice Johnson", new Date(), "Chicago", SeatClass.STANDARD_CLASS, 100.0));
            bookings.add(new TrainBooking("B004", "Bob Brown", new Date(), "Boston", SeatClass.FIRST_CLASS, 250.0));

            // Invalid / missing data (to test exceptions)
            bookings.add(new FlightBooking("B005", "Sara Ali", new Date(), "Dubai", 300.0, 20.0, 55.0));   // luggage > 40
            bookings.add(new FlightBooking("B006", "Omar Khan", new Date(), "London", 150.0, 20.0));       // luggage missing
            bookings.add(new TrainBooking("B007", "Lina Saad", new Date(), "Denver", SeatClass.STANDARD_CLASS, 2500.0)); // distance > 2000
            bookings.add(new CarRentalBooking("B008", "Maha Nasser", new Date(), "Miami", 40.0));          // days missing
            bookings.add(new CarRentalBooking("B009", "Ali Hassan", new Date(), "Seattle", 45.0, 45));     // days > 30

            for (Booking booking : bookings) {
                try {
                    double total = computeTotalPrice(booking);
                    System.out.printf("%s -> Total Price: %.2f%n", booking, total);
                    logger.info("Booking {} priced successfully: {}", booking.getBookingId(), total);
                } catch (MissingInformationException e) {
                    System.out.println("Missing information: " + e.getMessage());
                    logger.error("MissingInformationException: {}", e.getMessage(), e);
                } catch (InvalidArgumentException e) {
                    System.out.println("Invalid argument: " + e.getMessage());
                    logger.error("InvalidArgumentException: {}", e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage(), e);
        } finally {
            logger.info("Application is stopping...");
        }
    }
}