package ru.desyatova.trsis.logger.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;


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
                .orElseThrow(()-> new RuntimeException());
    }
}
