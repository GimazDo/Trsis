package ru.desyatova.trsis.logger.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.desyatova.trsis.logger.entity.RentLog;
import ru.desyatova.trsis.logger.repository.RentLogRepository;

@Component
@RequiredArgsConstructor
public class RentLogConsumer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RentLogRepository rentLogRepository;

    @KafkaListener(topics = "rentLog", groupId = "rentLogger")
    void listener(String rentLog) throws JsonProcessingException {
        System.out.println("RECEIVE NEW MESSAGE " + rentLog.toString());
        RentLog log = objectMapper.readValue(rentLog, RentLog.class);
        rentLogRepository.save(log);
    }
}
