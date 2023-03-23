package com.fdmgroup.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RegisterService;
import com.fdmgroup.news.model.User;



@Controller
public class LoginAndRegisterController {
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private RegisterService registerService;
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		logService.isLoggedIn(model);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		logService.isLoggedIn(model);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user") User user,
			@RequestParam("confirmPassword") String confirmPassword, ModelMap model) {
		
		return registerService.registerSubmit(user, confirmPassword, model);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ModelAndView handleUsernameNotFoundException(UsernameNotFoundException ex) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notFound");
		mav.addObject("type", "user");
		mav.addObject("message", ex.getMessage());
		return mav;
	}
}
