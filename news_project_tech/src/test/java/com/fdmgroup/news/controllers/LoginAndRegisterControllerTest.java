package com.fdmgroup.news.controllers;

import com.fdmgroup.news.controller.LoginAndRegisterController;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginAndRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogService logService;

    @MockBean
    private RegisterService registerService;

    @Autowired
    private LoginAndRegisterController loginAndRegisterController;

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
        verify(logService, times(1)).isLoggedIn(any(ModelMap.class));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));
        verify(logService, times(1)).isLoggedIn(any(ModelMap.class));
    }

    @Test
    public void testRegisterSubmit() throws Exception {
        User user = new User();
        String confirmPassword = "password";
        given(registerService.registerSubmit(user, confirmPassword, new ModelMap())).willReturn("redirect:/home");

        String result = loginAndRegisterController.registerSubmit(user, confirmPassword, new ModelMap());

        verify(registerService, times(1)).registerSubmit(user, confirmPassword, new ModelMap());
        assertEquals("redirect:/home", result);
    }

    @Test
    public void testHandleUsernameNotFoundException() throws Exception {
        UsernameNotFoundException ex = new UsernameNotFoundException("User not found");

        ModelAndView mav = loginAndRegisterController.handleUsernameNotFoundException(ex);

        assertEquals("notFound", mav.getViewName());
        assertEquals("user", mav.getModel().get("type"));
        assertEquals("User not found", mav.getModel().get("message"));
    }
}


