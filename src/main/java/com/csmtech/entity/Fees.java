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
public class Fees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feesId;
	
	private Long fees;
	 @OneToOne
	 @JoinColumn(name= "branchId")
	private Branch branchId;
}
