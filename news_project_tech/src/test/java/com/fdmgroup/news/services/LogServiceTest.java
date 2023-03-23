package com.fdmgroup.news.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.security.DefaultUserDetailsService;
import com.fdmgroup.news.security.UserPrincipal;

@SpringBootTest
public class LogServiceTest {

    @Mock
    private DefaultUserDetailsService userService;

    @InjectMocks
    private LogService logService;

    @Test
    public void testIsLoggedIn_ReturnsFalseWhenNotLoggedIn() {
        // Set the authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        boolean result = logService.isLoggedIn();

        assertFalse(result);
    }

    @Test
    public void testIsLoggedIn_ReturnsTrueWhenLoggedIn() {
        // Set the authentication to a non-anonymous authentication
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean result = logService.isLoggedIn();

        assertTrue(result);
    }

    @Test
    public void testIsLoggedIn_ReturnsFalseWhenAnonymous() {
        // Set the authentication to an anonymous authentication
        Authentication authentication = mock(AnonymousAuthenticationToken.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean result = logService.isLoggedIn();

        assertFalse(result);
    }

    @Test
    public void testGetLoggedUser_ReturnsUserWhenLoggedIn() {
        // Create a mock user
        User user = new User();
        user.setId(1);

        // Create a mock user principal
        UserPrincipal userPrincipal = new UserPrincipal(user);

        // Mock the authentication object
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock the user service
        when(userService.findByUsername(userPrincipal.getUsername())).thenReturn(Optional.of(user));

        User result = logService.getLoggedUser();

        assertEquals(user, result);
    }

    @Test
    public void testGetLoggedUser_ReturnsNullWhenNotLoggedIn() {
        // Set the authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        User result = logService.getLoggedUser();

        assertNull(result);
    }

    @Test
    public void testIsLoggedIn_ModelMap_ReturnsFalseWhenNotLoggedIn() {
        // Set the authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        ModelMap model = new ModelMap();

        boolean result = logService.isLoggedIn(model);

        assertFalse(result);
        assertFalse(model.containsAttribute("loggedIn"));
        assertNull(model.get("firstname"));
        assertNull(model.get("user_id"));
    }

}

