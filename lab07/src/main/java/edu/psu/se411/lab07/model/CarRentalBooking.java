package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;

public class CarRentalBooking extends Booking {

    private double dailyRate;
    private Integer rentalDays; // null = not provided yet

    public CarRentalBooking(String bookingId, String customerName, Date travelDate,
                            String destinationCity, double dailyRate) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.dailyRate = dailyRate;
    }

    public CarRentalBooking(String bookingId, String customerName, Date travelDate,
                            String destinationCity, double dailyRate, int rentalDays) {
        this(bookingId, customerName, travelDate, destinationCity, dailyRate);
        this.rentalDays = rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double calculateTotalPrice() {
        validateValue(rentalDays, Config.MIN_RENTAL_DAYS, Config.MAX_RENTAL_DAYS, "Rental days");
        return dailyRate * rentalDays;
    }

    public double getDailyRate() { return dailyRate; }
    public Integer getRentalDays() { return rentalDays; }
}