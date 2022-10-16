package ru.desyatova.trsis.lab2.services.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.desyatova.trsis.lab2.entity.Rent;
import ru.desyatova.trsis.lab2.enums.RentStatus;
import ru.desyatova.trsis.lab2.exceptions.DuplicateRentException;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;
import ru.desyatova.trsis.lab2.services.RentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ru.desyatova.trsis.lab2.exceptions.DuplicateRentException.ALREADY_EXISTS;
import static ru.desyatova.trsis.lab2.exceptions.NotFoundException.RENT_NOT_FOUND;

@Service
public class RentServiceImpl implements RentService {

    private static final List<Rent> rents = new ArrayList<>();

    static {
        rents.add(new Rent(1L, "Тест", new BigDecimal(1000), RentStatus.AVAILABLE));
        rents.add(new Rent(2L, "Тест", new BigDecimal("9999.999"), RentStatus.RENTED));
    }

    public List<Rent> getAll() {
        return rents;
    }

    public void add(Rent rent) {
        if (rents.stream().anyMatch(r -> r.getId().equals(rent.getId())))
            throw new DuplicateRentException(String.format(ALREADY_EXISTS, rent.getId()));
        if (rent.getId() == null) {
            Long nextId = rents.stream().map(Rent::getId)
                    .max((o1, o2) -> (int) (o1 - o2))
                    .orElse(0L) + 1;
            rent.setId(nextId);
        }
        rents.add(rent);
    }

    public void delete(Long id) {
        Rent rent = getById(id);
        rents.remove(rent);
    }

    public void update(Rent rent) {
        Rent oldRent =getById(rent.getId());
        oldRent.setCost(rent.getCost());
        oldRent.setAddress(rent.getAddress());
        oldRent.setStatus(rent.getStatus());

    }

    public Rent getById(Long id) {
        return rents.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(RENT_NOT_FOUND, id)));
    }

}
