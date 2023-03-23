package com.fdmgroup.news.services;

import com.fdmgroup.news.model.User;

public interface IUserProfileService {
	
	User editUserDetails(User user);
	User displayUserDetails(User user);

}
