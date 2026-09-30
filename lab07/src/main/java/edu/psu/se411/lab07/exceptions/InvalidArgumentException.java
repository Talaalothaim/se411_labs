package edu.psu.se411.lab07.exceptions;

public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidArgumentException(String message) {
        super(message);
    }
}