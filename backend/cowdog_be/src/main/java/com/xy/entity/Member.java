package com.xy.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Member {
	@Id
	@OneToOne
	@JoinColumn(name="id")
	private String id;
	
	
	private String password;
	private String nickname;
	private String email;
	private boolean issuspended;
	private Time onlinetime;
	
	
}
