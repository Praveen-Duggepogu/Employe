package com.employe.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employe.Entity.Employe;


@Repository
public interface EmployeDao extends JpaRepository<Employe, Integer> {
	@Query("SELECT e FROM Employe e WHERE " +
            "e.name LIKE CONCAT(:query, '%')")
    List<Employe> searchEmployees(String query);
}
