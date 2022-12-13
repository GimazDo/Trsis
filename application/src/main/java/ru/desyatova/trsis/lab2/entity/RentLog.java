package ru.desyatova.trsis.lab2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.desyatova.trsis.lab2.enums.OperationType;
import ru.desyatova.trsis.lab2.enums.RentStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
public class RentLog {

    private Long id;
    private Long rentId;

    private OperationType operationType;
    private String address;
    private BigDecimal cost;

    private RentStatus status;

    private ZonedDateTime createdDate;

    public RentLog(Rent rent, OperationType type){
        this.address = rent.getAddress();
        this.cost = rent.getCost();
        this.rentId = rent.getId();
        this.status = rent.getStatus();
        this.operationType = type;
        this.createdDate =  ZonedDateTime.now();

    }
}
