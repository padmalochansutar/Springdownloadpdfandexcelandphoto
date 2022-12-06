package com.csmtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csmtech.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	@Query("From Registration where registrationId=:id")
	Registration getApplicantByapplicantId(Integer id);


}
