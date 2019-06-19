package com.trabalho.autostore.dao;

import com.trabalho.autostore.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDao extends JpaRepository<Group, Integer> {
    Group findByGrpCode(int grpCode);

    Group findByGrpName(String grpName);

    @Query("select g from Group g ORDER BY grpName")
    List<Group> findAll();

}
