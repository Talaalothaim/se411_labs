package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.SeatClass;

public class TrainBooking extends Booking {

    private SeatClass seatClass;
    private Double distance; // null = not provided yet

    public TrainBooking(String bookingId, String customerName, Date travelDate,
                        String destinationCity, SeatClass seatClass) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.seatClass = seatClass;
    }

    public TrainBooking(String bookingId, String customerName, Date travelDate,
                        String destinationCity, SeatClass seatClass, double distance) {
        this(bookingId, customerName, travelDate, destinationCity, seatClass);
        this.distance = distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public double calculateTotalPrice() {
        if (seatClass == null) {
            throw new MissingInformationException("Seat class is not provided for booking " + getBookingId());
        }
        validateValue(distance, Config.MIN_TRAIN_DISTANCE, Config.MAX_TRAIN_DISTANCE, "Distance");

        double rate;
        switch (seatClass) {
            case FIRST_CLASS:
                rate = Config.TRAIN_FIRST_CLASS_RATE;
                break;
            case STANDARD_CLASS:
            default:
                rate = Config.TRAIN_STANDARD_RATE;
                break;
        }
        return distance * rate;
    }

    public SeatClass getSeatClass() { return seatClass; }
    public Double getDistance() { return distance; }
}