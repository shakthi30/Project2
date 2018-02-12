package snippet;



import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.BlogDao;
import com.niit.Dao.FriendDao;
import com.niit.Model.BlogPost;
import com.niit.Model.User;





public class Test 
{
/*    
	@org.junit.Test
	public void test() {
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
		UserDao userDao = (UserDao) con.getBean("userDao");
		User user = new User();
		user.setUser_email("satan@gmail.com");
		user.setUser_firstName("shakthi");
		user.setUser_lastName("S");
		user.setUser_Name("shak3");
		user.setUser_online(true);
		user.setUser_phoneNumber(855323100);
		user.setUser_role("STUDENT");
		user.setUse_password("2330");
       
		userDao.insert(user);
		
		
	}*/
/*	@org.junit.Test
	public void jobTest()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        JobDao jobDao = (JobDao) con.getBean("jobDao");
        Job job = new Job();
        job.setCompanyName("Infosys");
        job.setJobDescription("c++");
        job.setJobTitle("programmer");
        job.setLocation("Bangalore");
        job.setPostedOn(new Date());
        job.setSalary(4000.00);
        job.setSkillsRequired("full stack");
        job.setYrsOfExp("2 years");
        jobDao.saveJob(job);
       
	}*/

/*	@org.junit.Test
	public void jobGetAll()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        JobDao jobDao = (JobDao) con.getBean("jobDao");
        List<Job> job = jobDao.getAllJobs();
        for(Job jo : job)
        {
        	System.out.println(jo.getId()+" "+jo.getCompanyName()+" "+jo.getJobDescription()+" "+jo.getJobTitle()
        	+" "+jo.getLocation()+" "+jo.getSkillsRequired()+" "+jo.getYrsOfExp()+" "+jo.getSalary());
        	
        	
        }
       
	}*/
/*	@org.junit.Test
	public void getJob()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        JobDao jobDao = (JobDao) con.getBean("jobDao");
        Job jo = jobDao.getJobById(1);
     
        	System.out.println(jo.getId()+" "+jo.getCompanyName()+" "+jo.getJobDescription()+" "+jo.getJobTitle()
        	+" "+jo.getLocation()+" "+jo.getSkillsRequired()+" "+jo.getYrsOfExp()+" "+jo.getSalary());
        	
       
	}*/
/*	@org.junit.Test
	public void addblog()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        BlogDao blogDao = (BlogDao) con.getBean("blogDao");
        BlogPost blog = new BlogPost();
        User user = new User();
        user.setUser_Name("shakthi");
        blog.setBlogContent("this is first try");
        blog.setBlogTitle("try");
        blog.setLikes(1);
        blog.setPostedBy(user);
        blog.setPostedOn(new Date());
        blogDao.saveBlogPost(blog);
       
	}*/
/*	@org.junit.Test
	public void getblogs()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        BlogDao blogDao = (BlogDao) con.getBean("blogDao");
       List<BlogPost> bp = blogDao.getBlogs(0);
       for(BlogPost jo : bp)
       {
       	System.out.println(jo.getBlogContent()+""+jo.getBlogTitle()+""+jo.getPostedBy());
       	
       	
       }
       
	}*/
		/*@org.junit.Test
	public void getblogs()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        BlogDao blogDao = (BlogDao) con.getBean("blogDao");
        BlogPost bp = blogDao.getBlogById(10);
        System.out.println(bp.getBlogTitle()+""+bp.getBlogContent());
	}*/
	@org.junit.Test
	public void suggestedUser()
	{
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext();
        con.scan("com.niit.*");
        con.refresh();
        FriendDao friendDao = (FriendDao) con.getBean("friendDao");
       List<User> suggested= friendDao.suggestedUsersList("shakthi");
       for(User user : suggested)
       {
    	   System.out.println(user.getUser_Name());
       }
	}
}