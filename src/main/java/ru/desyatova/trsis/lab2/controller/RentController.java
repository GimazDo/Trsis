package ru.desyatova.trsis.lab2.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.desyatova.trsis.lab2.dao.RentDao;
import ru.desyatova.trsis.lab2.entity.Rent;
import ru.desyatova.trsis.lab2.enums.RentStatus;
import ru.desyatova.trsis.lab2.exceptions.DuplicateRentException;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rent/")
public class RentController {

    private final RentDao rentDao;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Rent> getAll() {
        return rentDao.getAll();
    }

    @PostMapping
    public void add(@RequestParam String address, @RequestParam BigDecimal cost, @RequestParam String status) {
        Rent rent = new Rent(null, address, cost, RentStatus.getByDescription(status));
        rentDao.add(rent);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody Rent rent) {
        if (!id.equals(rent.getId())) throw new IllegalArgumentException("Id в пути не совпадает с id в теле");
        rentDao.update(rent);
    }

    @GetMapping("{id}")
    public Rent getById(@PathVariable Long id) {
        return rentDao.getById(id);
    }

    @DeleteMapping("{id}")
    public void delById(@PathVariable Long id) {
        rentDao.delete(id);
    }

    @PostMapping("{id}")
    public void addWithID(@PathVariable Long id, @RequestBody Rent rent) {
        if (!id.equals(rent.getId())) throw new IllegalArgumentException("Id в пути не совпадает с id в теле");
        rentDao.add(rent);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        HashMap<String, Object> message = new HashMap<>();
        message.put("timestamp", ZonedDateTime.now());
        message.put("error", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        HashMap<String, Object> message = new HashMap<>();
        message.put("timestamp", ZonedDateTime.now());
        message.put("error", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateRentException.class)
    public ResponseEntity<Object> handleDuplicate(DuplicateRentException ex) {
        HashMap<String, Object> message = new HashMap<>();
        message.put("timestamp", ZonedDateTime.now());
        message.put("error", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
