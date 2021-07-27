package com.xy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
public class MemberInfo {
	
	
	@Id
	@OneToOne(mappedBy = "id")
	private String id;
	
	private String age;
	private String gender;
	private boolean smoking;
	private String alcohol;
	private String religion;
	private String personality;
	private String interest;
	private String hobby;
	private double latitude;
	private double longitude;
	private double distance;
	private String address;

}
