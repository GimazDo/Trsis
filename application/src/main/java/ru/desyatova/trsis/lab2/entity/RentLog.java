package ru.desyatova.trsis.lab2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.desyatova.trsis.lab2.enums.OperationType;
import ru.desyatova.trsis.lab2.enums.RentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Data
@ToString
@NoArgsConstructor
public class RentLog {

    private Long id;
    private Long rentId;

    private OperationType operationType;
    private String address;
    private BigDecimal cost;

    private RentStatus status;
    private String createdDate;

    public RentLog(Rent rent, OperationType type){
        this.address = rent.getAddress();
        this.cost = rent.getCost();
        this.rentId = rent.getId();
        this.status = rent.getStatus();
        this.operationType = type;
        this.createdDate =  LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    }
}
