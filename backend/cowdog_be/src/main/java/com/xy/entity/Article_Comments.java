package com.xy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Article_Comments {//댓글 엔터티
	@Id@GeneratedValue
	Long no;//댓글 번호 겸 pk
	
	@ManyToOne
	@JoinColumn(name="ariticleno")
	private Article articleno;//이 댓글이 달린 글 번호
	private String id;//작성자
	private String regtime;//작성일
	private String content;//내용
	
	
	
	
	
	
	

}
