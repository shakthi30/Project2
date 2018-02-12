package com.niit.DaoImplementation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.NotificationDao;
import com.niit.Model.Notification;
import com.niit.Model.User;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Notification> getNotification(User username, int viewed) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Notification where userName= :username and viewed= :viewed");
		query.setString("username", username.getUser_Name());
		query.setInteger("viewed", viewed);
		List<Notification> notifications = query.list();
		session.getTransaction().commit();
		return notifications;
	}

	@Override
	public Notification updateNotification(int notificationid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Notification notification = (Notification) session.get(Notification.class, notificationid);
		notification.setViewed(true);
		session.update(notification);
		session.getTransaction().commit();
		return notification;
	}

}
