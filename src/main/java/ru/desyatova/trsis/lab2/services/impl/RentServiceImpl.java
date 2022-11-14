package ru.desyatova.trsis.lab2.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.desyatova.trsis.lab2.entity.Rent;
import ru.desyatova.trsis.lab2.enums.RentStatus;
import ru.desyatova.trsis.lab2.exceptions.DuplicateRentException;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;
import ru.desyatova.trsis.lab2.repository.RentRepository;
import ru.desyatova.trsis.lab2.services.RentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.desyatova.trsis.lab2.exceptions.DuplicateRentException.ALREADY_EXISTS;
import static ru.desyatova.trsis.lab2.exceptions.NotFoundException.RENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    public Rent add(Rent rent) {
        return rentRepository.save(rent);
    }

    public void delete(Long id) {
        rentRepository.deleteById(id);
    }

    public Rent update(Rent rent) {
        if(rentRepository.existsById(rent.getId()))
            return rentRepository.save(rent);
        throw  new NotFoundException(String.format(RENT_NOT_FOUND, rent.getId()));
    }

    public Rent getById(Long id) {
        Optional<Rent> optionalRent = rentRepository.findById(id);
        if(optionalRent.isEmpty())
            throw  new NotFoundException(String.format(RENT_NOT_FOUND, id));
        return optionalRent.get();
    }

}
