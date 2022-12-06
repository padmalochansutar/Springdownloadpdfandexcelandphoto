package com.csmtech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class College {

	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer collegeId;
	
private String collegeName;

}
