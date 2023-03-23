package com.fdmgroup.news.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.security.DefaultUserDetailsService;
import com.fdmgroup.news.security.UserPrincipal;

@Service
public class LogService {
	@Autowired
	private DefaultUserDetailsService userService;
	

	public boolean isLoggedIn() {
		return  SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}
	
	public User getLoggedUser() {
		if (isLoggedIn()) {
			UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userService.findByUsername(userPrincipal.getUsername()).get();
			return user;
		}
		return null;
	}

	public boolean isLoggedIn(ModelMap model) {

		boolean isLoggedIn = isLoggedIn();
		if (isLoggedIn) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("loggedIn", isLoggedIn);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("user_id", user.getId());
			
		}
		return isLoggedIn;
	}
	
}
