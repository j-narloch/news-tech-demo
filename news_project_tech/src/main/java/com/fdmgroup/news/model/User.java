package com.fdmgroup.news.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "uzer")
public class User {

	@Id
	@GeneratedValue
	private Integer userId;

	

	private String username, password, email, firstName, surName, phoneNumber;


	@ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private List<Role> roleList = new ArrayList<>();

	public User() {
	}
	public User(String username, String password, String email, String firstName, String surName, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.surName = surName;
		this.phoneNumber = phoneNumber;

	}
	

	public User(String username) {
		super();
		this.username = username;
	}

	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.setRole(role);
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Integer getId() {
		return userId;
	}

	public void setId(Integer id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setRole(Role role) {
		roleList.add(role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, password, surName, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(password, other.password)
				&& Objects.equals(surName, other.surName)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User: "  + username + ", email=" + email
				+ ", firstName=" + firstName + ", surName=" + surName + ", roleList="
				;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
