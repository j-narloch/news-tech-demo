package com.fdmgroup.news.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.news.model.Role;
import com.fdmgroup.news.repository.RoleRepository;
import com.fdmgroup.news.services.RoleService;

@SpringBootTest
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testFindByRoleName_WithExistingRoleName() {
        String roleName = "admin";
        Role role = new Role(roleName);
        Optional<Role> optionalRole = Optional.of(role);
        when(roleRepository.findByRoleName(roleName)).thenReturn(optionalRole);

        Role result = roleService.findByRoleName(roleName);

        assertEquals(role, result);
    }

    @Test
    public void testFindByRoleName_WithNonExistingRoleName() {
        String roleName = "non-existing-role";
        Optional<Role> optionalRole = Optional.empty();
        when(roleRepository.findByRoleName(roleName)).thenReturn(optionalRole);

        Role result = roleService.findByRoleName(roleName);

        assertEquals("ROLE_default role", result.getAuthority());
    }
}
