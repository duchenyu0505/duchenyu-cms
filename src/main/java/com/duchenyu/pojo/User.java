package com.duchenyu.pojo;

import java.util.Date;

public class User {

	private Integer id;
	private String username;
	private String headimg;
	private String password;
	private String nickname;
	private String birthday;
	private String gender;
	private Integer locked;
	private Integer score;
	private String role;
	private String url;
	private Date create_time;
	private Date update_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	
	public User(Integer id, String username, String headimg, String password, String nickname, String birthday,
			String gender, Integer locked, Integer score, String role, String url, Date create_time, Date update_time) {
		super();
		this.id = id;
		this.username = username;
		this.headimg = headimg;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.score = score;
		this.role = role;
		this.url = url;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", headimg=" + headimg + ", password=" + password
				+ ", nickname=" + nickname + ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked
				+ ", score=" + score + ", role=" + role + ", url=" + url + ", create_time=" + create_time
				+ ", update_time=" + update_time + "]";
	}
	
	

}
