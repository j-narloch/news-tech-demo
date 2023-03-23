package com.fdmgroup.news.services;

import com.fdmgroup.news.model.User;

public interface IUserService {
	User findByUsername(String username);

	User findByUserId(int buyer_id);
}
