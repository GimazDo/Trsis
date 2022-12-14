package ru.desyatova.trsis.lab2.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.desyatova.trsis.lab2.entity.RentLog;

@RequiredArgsConstructor
@Service
public class RentProducer {
    private final KafkaTemplate<String, RentLog> kafkaTemplate;

    public void send(RentLog rentLog) {

        kafkaTemplate.send("rentLog", rentLog);
    }
}
