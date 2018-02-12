package com.niit.Dao;

import com.niit.Model.User;

public interface UserDao {
	public void insert(User user);

	public User login(User user);

	public User getUserByUsername(String username);

	public void updateUser(User user);

}
