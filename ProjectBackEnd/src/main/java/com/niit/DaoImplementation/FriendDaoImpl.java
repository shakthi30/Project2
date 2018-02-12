package com.niit.DaoImplementation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.FriendDao;
import com.niit.Model.Friend;
import com.niit.Model.User;

@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<User> suggestedUsersList(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery(
				"select * from details where user_Name in(select user_Name from details where user_Name!= ? minus"
						+ "(select fromId from Friend_Table where toId=? union select toId from Friend_Table where fromId = ?))");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);

		query.addEntity(User.class);
		List<User> suggestedUser = query.list();
		session.getTransaction().commit();
		return suggestedUser;
	}

	@Override
	public void addFriend(Friend friend) {
		System.out.println("in dao impl" + friend.getFromId());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(friend);
		session.getTransaction().commit();

	}

	@Override
	public List<Friend> pendingRequests(String toId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Friend where toId= :toId and status = 'P'");
		query.setString("toId", toId);
		List<Friend> friends = query.list();
		session.getTransaction().commit();
		return friends;
	}

	@Override
	public void accept(Friend friend) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(friend);
		session.getTransaction().commit();

	}

	@Override
	public Friend getFriendById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Friend friend = (Friend) session.get(Friend.class, id);
		session.getTransaction().commit();
		return friend;
	}

	@Override
	public void delete(Friend friend) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(friend);
		session.getTransaction().commit();

	}

	@Override
	public List<User> listOfFriends(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("select * from details where user_Name in( select toId from Friend_Table where fromId=?"
						+ "and status = 'A' union select fromId from Friend_Table where toId = ? and status = 'A' )");
		query.setString(0, username);
		query.setString(1, username);
		query.addEntity(User.class);
		List<User> users = query.list();
		session.getTransaction().commit();
		return users;
	}

}
