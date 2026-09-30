package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public abstract class Booking {

    private String bookingId;
    private String customerName;
    private Date travelDate;
    private String destinationCity;

    public Booking(String bookingId, String customerName, Date travelDate, String destinationCity) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    // Each subclass provides its own pricing rule (polymorphism)
    public abstract double calculateTotalPrice();

    // Shared validation reused by all subclasses (code reuse)
    protected void validateValue(Number value, double min, double max, String fieldName) {
        if (value == null) {
            throw new MissingInformationException(
                    fieldName + " is not provided for booking " + bookingId);
        }
        double v = value.doubleValue();
        if (v < min || v > max) {
            throw new InvalidArgumentException(
                    fieldName + " must be between " + min + " and " + max
                    + " (given: " + v + ") for booking " + bookingId);
        }
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public Date getTravelDate() { return travelDate; }
    public String getDestinationCity() { return destinationCity; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + bookingId + ", " + customerName
                + ", " + destinationCity + ", " + travelDate + "]";
    }
}