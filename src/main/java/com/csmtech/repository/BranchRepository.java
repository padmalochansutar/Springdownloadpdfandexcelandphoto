package com.csmtech.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csmtech.entity.Branch;
import com.csmtech.entity.College;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
//    @Query("from Branch where collegeId=:cId")
//	List<Branch> findByCId(Integer cId);
//    
    
@Query("from Branch where collegeId=:cId")
	List<Branch> findbyCollegeId(Integer cId);


}
