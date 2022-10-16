package ru.desyatova.trsis.lab2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.desyatova.trsis.lab2.enums.RentStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    private Long id;
    private String address;
    private BigDecimal cost;
    private RentStatus status;
}
