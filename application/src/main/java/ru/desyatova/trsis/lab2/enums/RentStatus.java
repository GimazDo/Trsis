package ru.desyatova.trsis.lab2.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;

import java.util.Arrays;

import static ru.desyatova.trsis.lab2.exceptions.NotFoundException.RENT_STATUS_NOT_FOUND;

public enum RentStatus {
    AVAILABLE("Свободна"),
    RENTED("Арендована");

    private final String description;

    RentStatus(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public static RentStatus getByDescription(String description){
        return Arrays.stream(values())
                .filter(rentStatus -> rentStatus.description.equals(description))
                .findFirst()
                .orElseThrow(()-> new NotFoundException(String.format(RENT_STATUS_NOT_FOUND, description)));
    }
}
