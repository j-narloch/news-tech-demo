package com.fdmgroup.news.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.news.controller.UserProfileController;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.security.DefaultUserDetailsService;
import com.fdmgroup.news.security.UserPrincipal;
import com.fdmgroup.news.services.LogService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

	@Mock
	private DefaultUserDetailsService userService;
	
	@Mock
	private LogService login;
	
	@InjectMocks
	private UserProfileController userProfileController;
	
	@Test
	public void testEditUserDetailsPageA() throws Exception {
		User user = new User();
		user.setUsername("john");
		user.setFirstName("John");
		user.setSurName("Doe");
		user.setEmail("john.doe@example.com");
		user.setPhoneNumber("+1-555-1234567");

		when(login.getLoggedUser()).thenReturn(user);

		ModelAndView modelAndView = userProfileController.editUserDetails(new ModelMap());

		assertEquals(modelAndView.getViewName(), "editProfile");
		assertEquals(modelAndView.getModel().get("user"), user);
	}
	
	@Test
	public void testShowUserDetails() throws Exception {
		User user = new User();
		user.setUsername("john");
		user.setFirstName("John");
		user.setSurName("Doe");
		user.setEmail("john.doe@example.com");
		user.setPhoneNumber("+1-555-1234567");

		when(login.getLoggedUser()).thenReturn(user);

		ModelMap model = new ModelMap();
		String viewName = userProfileController.showUserDetails(model);

		assertEquals(viewName, "userProfile");
		assertEquals(model.get("userName"), "john");
		assertEquals(model.get("userFirstName"), "John");
		assertEquals(model.get("userSurName"), "Doe");
		assertEquals(model.get("userEmail"), "john.doe@example.com");
		assertEquals(model.get("userPhone"), "+1-555-1234567");
	}
	
	@Test
	public void testEditUserDetailsPageB() throws Exception {
		User user = new User();
		user.setUsername("john");
		user.setFirstName("John");
		user.setSurName("Doe");
		user.setEmail("john.doe@example.com");
		user.setPhoneNumber("+1-555-1234567");
		
		User updatedUser = new User();
		updatedUser.setUsername("john");
		updatedUser.setFirstName("John Updated");
		updatedUser.setSurName("Doe Updated");
		updatedUser.setEmail("john.doe.updated@example.com");
		updatedUser.setPhoneNumber("+1-555-1234567");

		when(login.getLoggedUser()).thenReturn(user);
		
		Authentication authentication = mock(Authentication.class);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		when(authentication.getPrincipal()).thenReturn(userPrincipal);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		ModelMap model = new ModelMap();
		String viewName = userProfileController.editUserDetails(updatedUser, model);

		assertEquals(viewName, "userProfile");
		assertEquals(model.get("userName"), "john");
		assertEquals(model.get("userFirstName"), "John Updated");
		assertEquals(model.get("userSurName"), "Doe Updated");
		assertEquals(model.get("userEmail"), "john.doe.updated@example.com");
		assertEquals(model.get("userPhone"), "+1-555-1234567");
		
		verify(userService).saveUser(user);
	}

}
