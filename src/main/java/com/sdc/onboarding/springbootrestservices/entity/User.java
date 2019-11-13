package com.sdc.onboarding.springbootrestservices.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String biodata;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		
	}

	public User(Long id, String username, String biodata, List<Post> posts) {
		super();
		this.id = id;
		this.username = username;
		this.biodata = biodata;
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBiodata() {
		return biodata;
	}

	public void setBiodata(String biodata) {
		this.biodata = biodata;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
