package com.fdmgroup.news.services;

import com.fdmgroup.news.model.Role;

public interface IRoleService {
	Role findByRoleName(String roleName);
}
