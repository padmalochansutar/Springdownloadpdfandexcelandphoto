package com.csmtech.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csmtech.entity.Branch;
import com.csmtech.entity.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {
	@Query("from College where collegeName=:cName")
	College findbyCollegename(String cName);

}
