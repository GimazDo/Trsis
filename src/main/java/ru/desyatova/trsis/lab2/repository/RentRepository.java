package ru.desyatova.trsis.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.desyatova.trsis.lab2.entity.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    boolean existsById(Long id);
}
