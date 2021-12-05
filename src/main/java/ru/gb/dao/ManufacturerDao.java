package ru.gb.dao;

import ru.gb.entity.Manufacturer;

public interface ManufacturerDao {
    Iterable<Manufacturer> findAll();
    Manufacturer findById(Long id);
    String findNameById(Long id);
    void insert(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void deleteById(Long id);
}
