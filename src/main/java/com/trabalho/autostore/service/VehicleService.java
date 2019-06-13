package com.trabalho.autostore.service;

import com.trabalho.autostore.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findByVclCode(int vclCode);

    void add(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Vehicle vehicle);

    long count();
}
