package ru.desyatova.trsis.lab2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.desyatova.trsis.lab2.enums.RentStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String address;
    private BigDecimal cost;
    @Enumerated(EnumType.STRING)
    private RentStatus status;
}
