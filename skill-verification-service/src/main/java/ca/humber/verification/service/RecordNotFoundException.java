package ca.humber.verification.service;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) { super("Verification record " + id + " was not found."); }
}
