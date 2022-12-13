package ru.desyatova.trsis.logger.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.desyatova.trsis.logger.entity.RentLog;
import ru.desyatova.trsis.logger.repository.RentLogRepository;

@Component
@RequiredArgsConstructor
public class RentLogConsumer {
    private final RentLogRepository rentLogRepository;
    @KafkaListener(topics = "rent", groupId = "rentLogger")
    void listener(RentLog rentLog){
        rentLogRepository.save(rentLog);
    }
}
