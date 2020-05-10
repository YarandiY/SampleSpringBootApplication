package com.example.SpringBootApp;

import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.repository.UserRepository;
import com.example.SpringBootApp.service.UserServiceImp;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@Mock
	static UserRepository userRepository;
	@InjectMocks
	static UserServiceImp userService;
	static List<User> users;
	static Integer userId = 3;

	@BeforeClass
	public static void beforeAll(){
		users = createUsers();
		userRepository = mock(UserRepository.class);
		when(userRepository.findAll()).thenReturn(users);
		User sampleUser = new User();
		when(userRepository.save(sampleUser)).thenReturn(sampleUser);
		when(userRepository.findById(userId)).thenReturn(Optional.of(users.get(userId)));
		List<User> tmp = new ArrayList<>();
		sampleUser.setName("user3");
		sampleUser.setId(3);
		sampleUser.setAge(13);
		sampleUser.setPassword("$user$3");
		tmp.add(sampleUser);
		when(userRepository.findAllByName("user3")).thenReturn(tmp);
		when(userRepository.findByNameContainingIgnoreCase("user")).thenReturn(users);
		userService = new UserServiceImp(userRepository);
	}

	 public static List<User> createUsers(){
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("user" + i);
			user.setPassword("$user$" + i);
			user.setAge(i+10);
			user.setId(i);
			users.add(user);
		}
		return users;
	}



	@Test
	public void getUsers() {
		System.out.println(users.size());
		List<User> result = userService.getUsers();
		assertEquals(users, result);
	}
	@Test
	public void addUser() {
		UserD userD= new UserD();
		userD.setName("TEST01");
		userD.setPassword("TEST01$");
		userD.setAge(15);
		User result = userService.addUser(userD);
		assertEquals(15, result.getAge());
		assertEquals("TEST01", result.getName());
		assertEquals("TEST01$", result.getPassword());
	}

	@Test
	public void deleteUser(){
		userService.deleteUser(userId);
	}

	@Test
	public void getUser(){
		User result = userService.getUser(userId);
		assertEquals(users.get(3),result);
	}

	@Test
	public void updateUser(){
		UserD userD = new UserD();
		userD.setName("yalda");
		User result = userService.updateUser(userId, userD);
		assertEquals("yalda", result.getName());
		assertEquals(users.get(userId).getPassword(), result.getPassword());
	}

	@Test
	public void getUsersByName(){
		List<User> result = userService.getUsersByName("user3");
		assertEquals(3,result.get(0).getId());
	}

	@Test
	public void searchByName(){
		List<User> result = userService.searchByName("user");
		assertEquals(result,users);
	}

}
