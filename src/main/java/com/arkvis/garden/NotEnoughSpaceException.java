package com.arkvis.garden;

public class NotEnoughSpaceException extends RuntimeException {

    public NotEnoughSpaceException(String message) {
        super(message);
    }
}
