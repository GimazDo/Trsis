package ru.desyatova.trsis.lab2.services;

import ru.desyatova.trsis.lab2.entity.Rent;

public interface RentService {
    public Rent add(Rent rent);
    public void delete(Long id);
    public Rent update(Rent rent);
    public Rent getById(Long id);
}
