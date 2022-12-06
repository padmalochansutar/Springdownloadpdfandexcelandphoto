package com.csmtech.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
public class Registration implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer registrationId;

	
	private String applicantName;
    
	
	private String email;

	
	private Long mobileNo;
	
    private Integer age;

	private String idProof;
    
	private String course;
	
	 @ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "collegeId")
	private  College college;
	
	 @ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "branchId")
	private Branch branch;
	 
	 private String isDelete;

	
	
}
