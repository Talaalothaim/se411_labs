package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;

public class FlightBooking extends Booking {

    private double basePrice;
    private double includedLuggageWeight;
    private Double luggageWeight; // null = not provided yet

    public FlightBooking(String bookingId, String customerName, Date travelDate,
                         String destinationCity, double basePrice, double includedLuggageWeight) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.basePrice = basePrice;
        this.includedLuggageWeight = includedLuggageWeight;
    }

    public FlightBooking(String bookingId, String customerName, Date travelDate,
                         String destinationCity, double basePrice, double includedLuggageWeight,
                         double luggageWeight) {
        this(bookingId, customerName, travelDate, destinationCity, basePrice, includedLuggageWeight);
        this.luggageWeight = luggageWeight;
    }

    public void setLuggageWeight(Double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double calculateTotalPrice() {
        validateValue(luggageWeight, Config.MIN_LUGGAGE_WEIGHT, Config.MAX_LUGGAGE_WEIGHT, "Luggage weight");
        double extraWeight = Math.max(0, luggageWeight - includedLuggageWeight);
        return (basePrice + extraWeight * Config.EXTRA_LUGGAGE_RATE) * (1 + Config.TAX_RATE);
    }

    public double getBasePrice() { return basePrice; }
    public double getIncludedLuggageWeight() { return includedLuggageWeight; }
    public Double getLuggageWeight() { return luggageWeight; }
}