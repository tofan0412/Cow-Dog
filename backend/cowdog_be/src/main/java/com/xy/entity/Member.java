package com.xy.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="memberinfo")
	private MemberInfo memberinfo;
	
	private String memberid;
	
	@OneToMany(mappedBy = "member_id")//이 멤버가 쓴 게시물들
	private List<Article> articles=new ArrayList<Article>();
	
	@Column(name="password")
	private String password;
	private String nickname;
	private String email;
	private boolean issuspended;
	private String onlinetime;
	
	public void addArticle(Article article) {
		this.articles.add(article);
		if(article.getMember_id()!=this) {
			article.setMember_id(this);
		}
	}
	
	
	
}
