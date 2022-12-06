package com.csmtech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class Branch {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer branchId;
	
private String branchName;
@OneToOne
@JoinColumn(name="collegeId")
private College college;

}
