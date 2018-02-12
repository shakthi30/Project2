package com.niit.Dao;

import java.util.List;

import com.niit.Model.Friend;
import com.niit.Model.User;

public interface FriendDao {
	public List<User> suggestedUsersList(String username);

	public void addFriend(Friend friend);

	public Friend getFriendById(int id);

	public List<Friend> pendingRequests(String toId);

	public void accept(Friend friend);

	public void delete(Friend friend);

	public List<User> listOfFriends(String username);
}
