package com.trabalho.autostore.dao;

import com.trabalho.autostore.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusDao extends JpaRepository<Status, Integer> {
    Status findByStsCode(int stsCode);

    @Query("select s from Status s ORDER BY stsName")
    List<Status> findAll();
}
