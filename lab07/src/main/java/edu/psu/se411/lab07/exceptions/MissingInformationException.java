package edu.psu.se411.lab07.exceptions;

public class MissingInformationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MissingInformationException(String message) {
        super(message);
    }
}