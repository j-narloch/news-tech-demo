package com.fdmgroup.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.security.DefaultUserDetailsService;
import com.fdmgroup.news.security.UserPrincipal;
import com.fdmgroup.news.services.LogService;

@Controller
public class UserProfileController {
	
	@Autowired
	private DefaultUserDetailsService userService;
	
	@Autowired
	private LogService login;
	
	@GetMapping("/editProfile")
	public ModelAndView editUserDetails(ModelMap model) {
		login.isLoggedIn(model);
		ModelAndView modelAndView = new ModelAndView("editProfile");
		User loggedUser = login.getLoggedUser();
		modelAndView.addObject("user", loggedUser);

		return modelAndView;
	}
	
	
	@GetMapping("/userProfile")
	public String showUserDetails(ModelMap model) {
		login.isLoggedIn(model);
		User loggedInUser = login.getLoggedUser();
		
		model.addAttribute("userName", loggedInUser.getUsername());
		model.addAttribute("userFirstName", loggedInUser.getFirstName());
		model.addAttribute("userSurName", loggedInUser.getSurName());
		model.addAttribute("userEmail", loggedInUser.getEmail());
		model.addAttribute("userPhone", loggedInUser.getPhoneNumber());
		
		
		
		return "userProfile";
	}
	
	@PostMapping("/editProfile")
	public String editUserDetails(@ModelAttribute("user") User updatedUser, ModelMap model) {
		System.out.println("current user updated " + updatedUser);
		login.isLoggedIn(model);
	 
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User loggedInUser = ((UserPrincipal)authentication.getPrincipal()).getUser();
	 
	    loggedInUser.setFirstName(updatedUser.getFirstName());
	    loggedInUser.setSurName(updatedUser.getSurName());
	    loggedInUser.setEmail(updatedUser.getEmail());
	    loggedInUser.setPhoneNumber(updatedUser.getPhoneNumber());
	    
	    model.addAttribute("userName", loggedInUser.getUsername());
		model.addAttribute("userFirstName", loggedInUser.getFirstName());
		model.addAttribute("userSurName", loggedInUser.getSurName());
		model.addAttribute("userEmail", loggedInUser.getEmail());
		model.addAttribute("userPhone", loggedInUser.getPhoneNumber());
	    
	    User savedUser = userService.saveUser(loggedInUser);
	 
	    return "userProfile";
	}

}
