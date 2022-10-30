package ru.desyatova.trsis.lab2.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.desyatova.trsis.lab2.exceptions.ForbiddenException;
import ru.desyatova.trsis.lab2.services.impl.RentServiceImpl;
import ru.desyatova.trsis.lab2.entity.Rent;
import ru.desyatova.trsis.lab2.enums.RentStatus;
import ru.desyatova.trsis.lab2.exceptions.DuplicateRentException;
import ru.desyatova.trsis.lab2.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rent/")
public class RentController {

    private final RentServiceImpl rentServiceImpl;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Rent> getAll() {
        return rentServiceImpl.getAll();
    }

    @PostMapping
    public Rent add(@RequestParam String address, @RequestParam BigDecimal cost, @RequestParam String status, Principal principal) {
        if (principal == null) throw new ForbiddenException();
        Rent rent = new Rent(null, address, cost, RentStatus.getByDescription(status));
        return rentServiceImpl.add(rent);
    }

    @PutMapping("{id}")
    public Rent update(@PathVariable Long id, @RequestBody Rent rent, Principal principal) {
        if (principal == null) throw new ForbiddenException();
        if (!id.equals(rent.getId())) throw new IllegalArgumentException("Id в пути не совпадает с id в теле");
        return rentServiceImpl.update(rent);
    }

    @GetMapping("{id}")
    public Rent getById(@PathVariable Long id) {
        return rentServiceImpl.getById(id);
    }

    @DeleteMapping("{id}")
    public void delById(@PathVariable Long id, Principal principal) {
        if (principal == null) throw new ForbiddenException();
        rentServiceImpl.delete(id);
    }

    @PostMapping("{id}")
    public Rent addWithID(@PathVariable Long id, @RequestBody Rent rent, Principal principal) {
        if (principal == null) throw new ForbiddenException();
        if (!id.equals(rent.getId())) throw new IllegalArgumentException("Id в пути не совпадает с id в теле");
        return rentServiceImpl.add(rent);
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
