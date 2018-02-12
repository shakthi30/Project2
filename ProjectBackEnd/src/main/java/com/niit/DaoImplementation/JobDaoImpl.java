package com.niit.DaoImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.JobDao;
import com.niit.Model.Job;

@Repository("jobDao")

public class JobDaoImpl implements JobDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveJob(Job job) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(job);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public List<Job> getAllJobs() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Job");
		List<Job> job = query.list();
		session.getTransaction().commit();
		session.close();
		return job;
	}

	@Override
	public Job getJobById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Job where id= :id");
		query.setInteger("id", id);
		Job job = (Job) query.uniqueResult();
		return job;
	}

}
