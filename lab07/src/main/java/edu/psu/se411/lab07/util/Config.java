package edu.psu.se411.lab07.util;

public class Config {

    public final static double EXTRA_LUGGAGE_RATE = 5.0;
    public final static double TRAIN_STANDARD_RATE = 4.0;
    public final static double TRAIN_FIRST_CLASS_RATE = 6.0;
    public final static double MAX_LUGGAGE_WEIGHT = 40.0;
    public final static double MIN_LUGGAGE_WEIGHT = 0.0;
    public final static double MAX_TRAIN_DISTANCE = 2000.0;
    public final static double MIN_TRAIN_DISTANCE = 1.0;
    public final static int MAX_RENTAL_DAYS = 30;
    public final static int MIN_RENTAL_DAYS = 1;
    public final static double TAX_RATE = 0.07;

    private Config() {
        // prevent instantiation
    }
}