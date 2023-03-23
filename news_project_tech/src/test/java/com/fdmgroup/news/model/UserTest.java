package com.fdmgroup.news.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserTest {

	@InjectMocks
	private User user;

	@Mock
	private Role role;

	@BeforeEach
	public void setUp() {
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		user.setUsername("test");
		user.setPassword("password");
		user.setRoleList(roleList);
	}

	@Test
	public void testGetId() {
		assertEquals(null, user.getId());
	}

	@Test
	public void testSetId() {
		user.setId(1);
		assertEquals(1, user.getId());
	}

	@Test
	public void testGetUsername() {
		assertEquals("test", user.getUsername());
	}

	@Test
	public void testSetUsername() {
		user.setUsername("newTest");
		assertEquals("newTest", user.getUsername());
	}

	@Test
	public void testGetPassword() {
		assertEquals("password", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("newPassword");
		assertEquals("newPassword", user.getPassword());
	}

	@Test
	public void testGetEmail() {
		assertEquals(null, user.getEmail());
	}

	@Test
	public void testSetEmail() {
		user.setEmail("test@test.com");
		assertEquals("test@test.com", user.getEmail());
	}

	@Test
	public void testGetFirstName() {
		assertEquals(null, user.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		user.setFirstName("firstName");
		assertEquals("firstName", user.getFirstName());
	}

	@Test
	public void testGetSurName() {
		assertEquals(null, user.getSurName());
	}

	@Test
	public void testSetSurName() {
		user.setSurName("surName");
		assertEquals("surName", user.getSurName());
	}

	@Test
	public void testGetRoleList() {
		List<Role> roles = user.getRoleList();
		assertEquals(1, roles.size());
		assertEquals(role, roles.get(0));
	}

	@Test
	public void testSetRoleList() {
		Role newRole = new Role("newRole");
		List<Role> roleList = new ArrayList<>();
		roleList.add(newRole);
		user.setRoleList(roleList);
		assertEquals(1, user.getRoleList().size());
		assertEquals(newRole, user.getRoleList().get(0));
	}

	@Test
	public void testSetRole() {
		user.setRole(new Role("newRole"));
		assertEquals(2, user.getRoleList().size());
	}

	@Test
	public void testHashCode() {
		User user1 = new User("test", "password");
		User user2 = new User("test", "password");
		assertTrue(user1.hashCode() == user2.hashCode());
	}

	@Test
	public void testEquals() {
		User user1 = new User("test", "password");
		User user2 = new User("test", "password");
		assertTrue(user1.equals(user2));
	}
	
    @Test
    public void testToString() {
        String expected = "User: username, email=email, firstName=firstName, surName=surName, roleList=";
        assertEquals(expected, user.toString());
    }
    
    @Test
    public void testGetPhoneNumber() {
        String expected = "phoneNumber";
        assertEquals(expected, user.getPhoneNumber());
    }
    
    @Test
    public void testSetPhoneNumber() {
        String expected = "newPhoneNumber";
        user.setPhoneNumber(expected);
        assertEquals(expected, user.getPhoneNumber());
    }

    @Test
    public void testConstructorWithUsername() {
        User user = new User("test");
        assertAll(
            () -> assertEquals("test", user.getUsername()),
            () -> assertNull(user.getPassword()),
            () -> assertTrue(user.getRoleList().isEmpty())
        );
    }

    @Test
    public void testConstructorWithUsernameAndPasswordAndRole() {
        Role role = mock(Role.class);
        User user = new User("test", "password", role);
        assertAll(
            () -> assertEquals("test", user.getUsername()),
            () -> assertEquals("password", user.getPassword()),
            () -> assertEquals(1, user.getRoleList().size()),
            () -> assertTrue(user.getRoleList().contains(role))
        );
    }
	
}
