package com.employe.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employe.Entity.TimeSheet;



@Repository
public interface TimesheetDao extends JpaRepository<TimeSheet, Integer> {

}
