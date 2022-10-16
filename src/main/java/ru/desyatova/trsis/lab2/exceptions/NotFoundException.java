package ru.desyatova.trsis.lab2.exceptions;


public class NotFoundException extends RuntimeException {

    public static String RENT_NOT_FOUND = "Аренда с id %d не найдена";
    public static String RENT_STATUS_NOT_FOUND = "Статус с названием %s не найден";

    public NotFoundException(String message) {
        super(message);
    }
}
