package com.lkf.example.model.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by kaifeng on 2017/5/9.
 */
public class User {
	@Id
	private Long id;
	private String username;
	private Integer age;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User(Long id, String username, Integer age) {
		this.id = id;
		this.username = username;
		this.age = age;

	}
}