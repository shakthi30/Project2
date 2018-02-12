package com.niit.Dao;

import java.util.List;

import com.niit.Model.Notification;
import com.niit.Model.User;

public interface NotificationDao {
	public List<Notification> getNotification(User username, int viewed);

	public Notification updateNotification(int notificationid);
}
