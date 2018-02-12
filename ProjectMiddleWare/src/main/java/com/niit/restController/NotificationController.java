package com.niit.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.NotificationDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Notification;
import com.niit.Model.User;

@Controller
public class NotificationController {
	@Autowired
	NotificationDao notificationDao;

	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/getNotifications/{viewed}", method = RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int viewed, HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			List<Notification> notifications = notificationDao.getNotification(user, viewed);
			return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/updateNotification/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNotification(HttpSession session, @PathVariable int id) {
		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			Notification notification = notificationDao.updateNotification(id);

			return new ResponseEntity<Notification>(notification, HttpStatus.OK);
		}
	}
}
