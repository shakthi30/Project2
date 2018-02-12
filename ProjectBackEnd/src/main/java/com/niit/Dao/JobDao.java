package com.niit.Dao;

import java.util.List;

import com.niit.Model.Job;

public interface JobDao {
	public void saveJob(Job job);

	public List<Job> getAllJobs();

	public Job getJobById(int id);
}
