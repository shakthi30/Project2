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

import com.niit.Dao.FriendDao;
import com.niit.Model.Friend;
import com.niit.Model.User;

@Controller
public class FriendController {
	@Autowired
	FriendDao friendDao;

	@RequestMapping(value = "/suggested_users", method = RequestMethod.GET)
	public ResponseEntity<?> suggestedUsers(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			List<User> suggestedUsers = friendDao.suggestedUsersList(username);
			return new ResponseEntity<List<User>>(suggestedUsers, HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/addFriend/{toId}", method = RequestMethod.POST)
	public ResponseEntity<?> addFriend(@PathVariable String toId, HttpSession session) {
		System.out.println("in add friend");
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			Friend friend = new Friend();
			friend.setToId(toId);
			friend.setFromId(username);
			friend.setStatus('P');
			System.out.println(friend.getToId() + "" + friend.getFromId() + "" + friend.getStatus());
			friendDao.addFriend(friend);
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/pendingRequests", method = RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			List<Friend> friends = friendDao.pendingRequests(username);
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/accept_Request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> acceptRequest(@PathVariable int id, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {

			Friend friend = friendDao.getFriendById(id);
			friend.setStatus('A');
			friendDao.accept(friend);

			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/delete_Request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> deleteRequest(HttpSession session, @PathVariable int id) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			Friend friend = friendDao.getFriendById(id);
			friendDao.delete(friend);

			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			List<User> friends = friendDao.listOfFriends(username);
			return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
		}
	}
}
