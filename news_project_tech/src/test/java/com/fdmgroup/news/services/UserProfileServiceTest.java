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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repository.UserRepository;
import com.fdmgroup.news.services.UserProfileService;

@SpringBootTest
public class UserProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserProfileService userProfileService;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testEditUserDetails_WithValidUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setFirstName("John");
        user.setSurName("Doe");

        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));

        User updatedUser = new User();
        updatedUser.setUsername("testuser");
        updatedUser.setFirstName("Jane");
        updatedUser.setSurName("Doe");
    }

    @Test
    public void testEditUserDetails_WithInvalidUser() {
        when(userRepository.findByUsername("invaliduser")).thenReturn(Optional.empty());

        User user = new User();
        user.setUsername("invaliduser");
        user.setFirstName("John");
        user.setSurName("Doe");

        try {
            userProfileService.editUserDetails(user);
        } catch (UsernameNotFoundException ex) {
            assertEquals("User with username invaliduser not found", ex.getMessage());
        }
    }

    @Test
    public void testDisplayUserDetails_WithValidUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setFirstName("John");
        user.setSurName("Doe");
    }

    @Test
    public void testDisplayUserDetails_WithInvalidUser() {
        when(userRepository.findByUsername("invaliduser")).thenReturn(Optional.empty());

        User user = new User();
        user.setUsername("invaliduser");
        user.setFirstName("John");
        user.setSurName("Doe");

        try {
            userProfileService.displayUserDetails(user);
        } catch (UsernameNotFoundException ex) {
            assertEquals("User with username invaliduser not found", ex.getMessage());
        }
    }
}

