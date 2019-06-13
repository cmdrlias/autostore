package com.trabalho.autostore.dao;

import com.trabalho.autostore.model.Group;
import com.trabalho.autostore.model.Status;
import com.trabalho.autostore.model.User;
import com.trabalho.autostore.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleDao extends JpaRepository<Vehicle, Integer> {
    Vehicle findByVclCode(int vclCode);

    Vehicle findByVclName(String vclName);

    @Query("select v from Vehicle v ORDER BY vclName")
    List<Vehicle> findAll();

    @Query("select count(v) from Vehicle v")
    long count();
}

//    select v.vcl_name, v.vcl_plaque, g.grp_name from vehicle_group vg
//        inner join vehicle v on vg.vcl_code = v.vcl_code
//        inner join group_t g on vg.grp_code = g.grp_code;

