package com.fdmgroup.news.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import com.fdmgroup.news.model.Role;
import com.fdmgroup.news.repository.RoleRepository;

@SpringBootTest
public class RoleTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private Role role;

    @Test
    public void testGetAuthority() {
        role.setRoleName("admin");
        assertEquals("ROLE_admin", role.getAuthority());
    }

    @Test
    public void testFindByName_WithExistingRole() {
        Role expectedRole = new Role("admin");
        when(roleRepository.findByRoleName("admin")).thenReturn(Optional.of(expectedRole));

        Optional<Role> actualRole = roleRepository.findByRoleName("admin");
        assertEquals(expectedRole, actualRole.get());
    }

    @Test
    public void testFindByName_WithNonExistingRole() {
        when(roleRepository.findByRoleName("non-existing-role")).thenReturn(Optional.empty());

        Optional<Role> actualRole = roleRepository.findByRoleName("non-existing-role");
        assertEquals(Optional.empty(), actualRole);
    }

    @Test
    public void testEquals_WithSameObject() {
        Role role1 = new Role("admin");
        Role role2 = role1;
        assertEquals(role1, role2);
    }

    @Test
    public void testEquals_WithEqualObjects() {
        Role role1 = new Role("admin");
        Role role2 = new Role("admin");
        assertEquals(role1, role2);
    }

    @Test
    public void testEquals_WithDifferentObjects() {
        Role role1 = new Role("admin");
        Role role2 = new Role("user");
        assertEquals(false, role1.equals(role2));
    }
    
    @Test
    public void testGetId() {
        role.setId(1);
        assertEquals(1, role.getId());
    }

    @Test
    public void testGetRoleName() {
        role.setRoleName("admin");
        assertEquals("admin", role.getRoleName());
    }

    @Test
    public void testSetRoleName() {
        role.setRoleName("user");
        assertEquals("user", role.getRoleName());
    }

    @Test
    public void testHashCode_WithSameObject() {
        Role role1 = new Role("admin");
        Role role2 = role1;
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    public void testHashCode_WithEqualObjects() {
        Role role1 = new Role("admin");
        Role role2 = new Role("admin");
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    public void testHashCode_WithDifferentObjects() {
        Role role1 = new Role("admin");
        Role role2 = new Role("user");
        assertEquals(false, role1.hashCode() == role2.hashCode());
    }
}

