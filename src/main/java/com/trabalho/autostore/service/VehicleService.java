package com.trabalho.autostore.service;

import com.trabalho.autostore.model.Group;
import com.trabalho.autostore.model.Status;
import com.trabalho.autostore.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findByVclCode(int vclCode);

    Vehicle findByVclName(String vclName);

    void add(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Vehicle vehicle);

    long count();

    // Find by group

    List<Group> findAllGroups();

    Group findGrpByCode(int grpCode);

    Group findGrpByName(String grpName);

    // Find by status

    List<Status> findAllStatus();

    Status findStsByCode(int stsCode);
}
