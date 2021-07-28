package com.xy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.type.TimestampType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	
	@Id @GeneratedValue
	private Long articleno;//pk
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")//컬럼 이름을 member_id로 하겠따.
	private Member member_id;//이 게시물을 누가 썼는지
	
	
	
	private String title;
	private String content;
	private TimestampType regtime;
	private String image;
	private double longitude;
	private double distance;
	private String address;
	
}
