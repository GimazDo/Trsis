package ru.desyatova.trsis.lab2.exceptions;

public class DuplicateRentException extends RuntimeException{
    public static final String ALREADY_EXISTS = "Аренда с id %d уже существует";
    public DuplicateRentException(String message) {
        super(message);
    }
}
