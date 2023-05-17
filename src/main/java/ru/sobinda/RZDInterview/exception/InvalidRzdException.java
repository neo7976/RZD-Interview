package ru.sobinda.RZDInterview.exception;

public class InvalidRzdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidRzdException(String msg) {
        super(msg);
    }
}

