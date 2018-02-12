package com.niit.restController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.niit.Dao.UserDao;
import com.niit.Model.User;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/insert_UserDetails", method = RequestMethod.POST)
	public ResponseEntity<?> saveUserDetails(@RequestBody User user) {
		try {
			userDao.insert(user);
		} catch (Exception e) {

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		System.out.println("usercontrller java");
		User validUser = userDao.login(user);
		System.out.println(validUser);
		if (validUser == null) {
			System.out.println("nulled");
			return new ResponseEntity<User>(validUser, HttpStatus.BAD_REQUEST);
		} else {
			validUser.setUser_online(true);
			System.out.println(validUser.getUser_firstName());
			System.out.println("role" + validUser.getUser_role());
			session.setAttribute("username", validUser.getUser_Name());
			userDao.updateUser(validUser);
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			System.out.println("in log out in java");
			User user = userDao.getUserByUsername(username);
			user.setUser_online(false);
			session.removeAttribute("username");
			userDao.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

}
