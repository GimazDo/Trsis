package ru.desyatova.trsis.logger.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.desyatova.trsis.logger.entity.RentLog;
import ru.desyatova.trsis.logger.repository.RentLogRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rent/")
public class RentController {

    private final RentLogRepository rentLogRepository;
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RentLog> getAll() {
        return rentLogRepository.findAll();
    }

}
