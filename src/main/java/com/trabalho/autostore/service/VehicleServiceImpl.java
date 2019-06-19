package com.trabalho.autostore.service;

import com.trabalho.autostore.dao.GroupDao;
import com.trabalho.autostore.dao.StatusDao;
import com.trabalho.autostore.dao.VehicleDao;
import com.trabalho.autostore.model.Group;
import com.trabalho.autostore.model.Status;
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

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private StatusDao statusDao;

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
    public Vehicle findByVclName(String vclName) {
        return vehicleDao.findByVclName(vclName);
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

    @Override
    public List<Group> findAllGroups() {
        return groupDao.findAll();
    }

    @Override
    public Group findGrpByCode(int grpCode) {
        return groupDao.findByGrpCode(grpCode);
    }

    @Override
    public Group findGrpByName(String grpName) {
        return groupDao.findByGrpName(grpName);
    }

    @Override
    public List<Status> findAllStatus() {
        return statusDao.findAll();
    }

    @Override
    public Status findStsByCode(int stsCode) {
        return statusDao.findByStsCode(stsCode);
    }
}
