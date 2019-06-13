package com.trabalho.autostore.service;

import com.trabalho.autostore.dao.VehicleDao;
import com.trabalho.autostore.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("VehicleService")
@Transactional
public class VehicleServiceImpl extends BaseService implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    @Value("${system.web.address}")
    private String systemWebAddress;

    @Override
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    @Override
    public Vehicle findByVclCode(int vclCode) {
        return vehicleDao.findByVclCode(vclCode);
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicleDao.delete(vehicle);
    }

    @Override
    public long count() {
        return vehicleDao.count();
    }
}
