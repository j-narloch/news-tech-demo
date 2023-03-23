package com.fdmgroup.news.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class UserProfile {
	
	@Id
	@GeneratedValue
	private Integer profileId;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	

}
