package com.fdmgroup.news.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repository.UserRepository;
import com.fdmgroup.news.security.DefaultUserDetailsService;
import com.fdmgroup.news.services.IRegisterService;
import com.fdmgroup.news.services.RegisterService;

@SpringBootTest
public class RegisterServiceTest {

    @Mock
    private DefaultUserDetailsService userService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private ModelMap model;

    @InjectMocks
    private RegisterService registerService;

    @Test
    public void testRegisterSubmit_WithExistingUser() {
        User user = new User();
        user.setUsername("existingUser");
        Optional<User> userFromDatabase = Optional.of(new User());
        when(userService.findByUsername(user.getUsername())).thenReturn(userFromDatabase);
        String result = registerService.registerSubmit(user, "password", model);
        assertEquals("register", result);
        assertEquals("This user name already exists", model.get("message"));
    }

    @Test
    public void testRegisterSubmit_WithPasswordMismatch() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("password");
        String confirmPassword = "wrongPassword";
        String result = registerService.registerSubmit(user, confirmPassword, model);
        assertEquals("register", result);
        assertEquals("Passwords do not match", model.get("message"));
    }

    @Test
    public void testRegisterSubmit_WithValidUser() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("password");
        String confirmPassword = "password";
        when(userService.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(encoder.encode(user.getPassword())).thenReturn("encodedPassword");
        String result = registerService.registerSubmit(user, confirmPassword, model);
        assertEquals("index", result);
        assertEquals(null, model.get("message"));
    }
}
