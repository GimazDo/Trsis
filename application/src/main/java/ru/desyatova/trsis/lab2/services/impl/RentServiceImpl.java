package ru.desyatova.trsis.lab2.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.desyatova.trsis.lab2.entity.Rent;
import ru.desyatova.trsis.lab2.entity.RentLog;
import ru.desyatova.trsis.lab2.enums.OperationType;
import ru.desyatova.trsis.lab2.enums.RentStatus;
import ru.desyatova.trsis.lab2.exceptions.DuplicateRentException;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;
import ru.desyatova.trsis.lab2.producer.RentProducer;
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
    private final RentProducer rentProducer;
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    public Rent add(Rent rent) {
        Rent newRent = rentRepository.save(rent);
        rentProducer.send(new RentLog(newRent, OperationType.CREATE));
        return newRent;

    }

    public void delete(Long id) {
        rentProducer.send(new RentLog(rentRepository.getReferenceById(id), OperationType.DELETE));
        rentRepository.deleteById(id);
    }

    public Rent update(Rent rent) {
        if(rentRepository.existsById(rent.getId())){
            Rent rent1 = rentRepository.save(rent);
            RentLog rentLog = new RentLog(rent1, OperationType.UPDATE);
            rentProducer.send(rentLog);
            return rent1;
        }
        throw  new NotFoundException(String.format(RENT_NOT_FOUND, rent.getId()));
    }

    public Rent getById(Long id) {
        Optional<Rent> optionalRent = rentRepository.findById(id);
        if(optionalRent.isEmpty())
            throw  new NotFoundException(String.format(RENT_NOT_FOUND, id));
        return optionalRent.get();
    }

}
