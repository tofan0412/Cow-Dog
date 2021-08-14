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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.type.TimestampType;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
	
	@Id
	@GeneratedValue
	private Long articleNo;//pk
	
	private String writer;
	private Long memberId;

	@JsonManagedReference
	@OneToMany(mappedBy = "articleno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Article_Comments> comments;// 이 게시물에 댓글들
	private String file_path;
	private String title;
	private String imgFullPath;
	private String content;
	private Timestamp regtime;
	private String image;
	private String tags;
}
