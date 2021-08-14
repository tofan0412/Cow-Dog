package com.xy.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.type.TimestampType;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	private Long articleNo;//pk
	
	private String writer;
	private Long memberId;
	
	
	@OneToMany(mappedBy = "articleno")
	private List<Article_Comments> comments;// 이 게시물에 댓글들
	
	
	private String title;
	private String content;
	private Timestamp regtime;
	private String file_path;
	private String imgFullPath;
	private double longitude;
	private double distance;
	private String address;
}
