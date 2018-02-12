package com.niit.restController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Job;
import com.niit.Model.User;

@Controller
public class JobController {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/add_Jobs", method = RequestMethod.POST)
	public ResponseEntity<?> addJobs(@RequestBody Job job, HttpSession session) {
		System.out.println("inside the java controller");
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<Job>(HttpStatus.UNAUTHORIZED);
		}

		User user = userDao.getUserByUsername(username);
		if (!user.getUser_role().equals("ADMIN")) {
			return new ResponseEntity<Job>(HttpStatus.UNAUTHORIZED);
		} else {
			job.setPostedOn(new Date());
			try {
				jobDao.saveJob(job);
			} catch (Exception e) {
				return new ResponseEntity<Job>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/jobsList", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {
		System.out.println("inside the java controller");
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<Job>(HttpStatus.UNAUTHORIZED);
		}

		else {
			List<Job> jobs = jobDao.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getJob/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<Job>(HttpStatus.UNAUTHORIZED);
		}

		else {
			System.out.println(id);
			Job job = jobDao.getJobById(id);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}

	}

}
