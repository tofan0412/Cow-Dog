package com.xy.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Member {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Column(name="id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="memberinfo")
	private MemberInfo memberinfo;
	
	private String memberid;
	
//	@OneToMany(mappedBy = "member_id")//이 멤버가 쓴 게시물들
//	private List<Article> articles=new ArrayList<Article>();

	@Column(name="password")
	private String password;
	private String nickname;
	private String email;
	private boolean issuspended;
	private String onlinetime;
	private String file_path;
	private String title;
	private String imgFullPath;
	private boolean login;
	private boolean manager;

	@ManyToMany
	@JoinTable(name = "MEMBER_ARTICLE",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "articleNo"))
	private List<Article> articles = new ArrayList<>(); // 다대다 관계

//	public void addArticle(Article article) {
//		this.articles.add(article);
//		if(article.getMember_id()!=this) {
//			article.setMember_id(this);
//		}
//	}
	
	
	
}
