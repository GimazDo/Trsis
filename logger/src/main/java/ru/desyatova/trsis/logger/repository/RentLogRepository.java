package ru.desyatova.trsis.logger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.desyatova.trsis.logger.entity.RentLog;
@Repository
public interface RentLogRepository extends JpaRepository<RentLog, Long> {
}
