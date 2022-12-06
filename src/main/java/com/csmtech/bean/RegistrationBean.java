package com.csmtech.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.csmtech.entity.Branch;
import com.csmtech.entity.College;

import lombok.Data;
@Data
public class RegistrationBean {

	
private Integer registrationId;

	
	private String applicantName;
    
	
	private String email;

	
	private Long mobileNo;
	
    private Integer age;

	private String idProof;
    
	private List<String> course;
	
	
	private  Integer collegeId;
	
	private Integer branchId;
	 
	 private String isDelete;

	
}
