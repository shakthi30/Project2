package com.niit.DaoImplementation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.UserDao;
import com.niit.Model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public void insert(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public User login(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println(user.getUser_firstName());
		System.out.println(user.getUse_password());
		Query query = session.createQuery("from User where user_Name= :username and use_password= :password");
		query.setString("username", user.getUser_Name());
		query.setString("password", user.getUse_password());
		User validUser = (User) query.uniqueResult();
		session.getTransaction().commit();
		return validUser;
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, username);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
	}

}
