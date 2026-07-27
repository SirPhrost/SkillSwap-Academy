package ca.humber.skillswap.service;

public class WorkshopNotFoundException extends RuntimeException {
    public WorkshopNotFoundException(Long id) {
        super("Workshop " + id + " was not found.");
    }
}
