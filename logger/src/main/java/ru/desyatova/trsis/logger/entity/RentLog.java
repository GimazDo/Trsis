package ru.desyatova.trsis.logger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.desyatova.trsis.logger.enums.OperationType;
import ru.desyatova.trsis.logger.enums.RentStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long rentId;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private String address;
    private BigDecimal cost;
    @Enumerated(EnumType.STRING)
    private RentStatus status;

    private ZonedDateTime createdDate;
}
