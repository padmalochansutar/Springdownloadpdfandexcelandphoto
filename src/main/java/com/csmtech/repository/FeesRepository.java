package com.csmtech.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csmtech.entity.Branch;
import com.csmtech.entity.Fees;

public interface FeesRepository extends JpaRepository<Fees, Integer> {
     @Query("select fees from  Fees where branchId=:branch")
     Long findByBId(Branch branch);

}
