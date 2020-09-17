package com.campfire;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.campfire.model.CF_Like;
import com.campfire.model.CF_Post;
import com.campfire.model.CF_User;
import com.campfire.repository.CF_LikeRepo;
import com.campfire.repository.CF_PostRepo;
import com.campfire.repository.CF_UserRepo;
import com.campfire.utilities.*; //repository.CF_UserRepo;

public class Driver {
	
    public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static CF_LikeRepo likeRepo = appContext.getBean("likeRepo", CF_LikeRepo.class);
	public static CF_PostRepo postRepo = appContext.getBean("postRepo", CF_PostRepo.class);
	public static CF_UserRepo userRepo = appContext.getBean("userRepo", CF_UserRepo.class);
	
	public static void main(String[] args) {
		System.out.println("DEBUG inserting Dummy Values");
		insertDummyValues();
		
		System.out.println("All our users: \n");
		for (CF_User u : userRepo.selectAll() ) {
			System.out.println(u);
		}
		
		System.out.println("All our posts: \n");
		for (CF_Post p : postRepo.selectAll() ) {
			System.out.println(p);
		}
		
	}
	
	public static void insertDummyValues() {
		//CF_Post post1 = new Post("");
		//CF_UserRepo userRepo = new CF_UserRepo();
		//	public CF_User(String cF_User_Username, String cF_User_Password, String cF_User_Email)

		CF_User user1 = new CF_User(
				"bobby123",
				"password", 
				"Construction Manager", 
				"loves hunting and fishing", 
				"Bobby",
				"Jean", 
				"bobbyboy@hotmail.com", 
				new Date(123),
				"image.png");
		userRepo.insert(user1);
		Date bday = new Date(123);
		//Date date= new Date(); //import java.util.Date;
		//long time = date.getTime();
		//Timestamp ts = new Timestamp(time);

		CF_User user2 = new CF_User(
				"cf1", 
				"cf1", 
				"u1",
				"Very boring person",
				"Cfuser",
				"One", 
				"Cfuser.One@CampFire.project2",
				bday,
				"cf1.pdf");
		userRepo.insert(user2);

		Date user3_bday=new java.sql.Date(new java.util.Date().getTime());
		CF_User user3 = new CF_User(
				"cf3", 
				"cf3", 
				"u3",
				"Very energetic person",
				"Cfuser",
				"Three", 
				"Cfuser.Three@CampFire.project2",
				user3_bday,
				"cf3.pdf");
		userRepo.insert(user3);

		Date user4_bday=new java.sql.Date(new java.util.Date().getTime());
		CF_User user4 = new CF_User(
				"cf4", 
				"cf4", 
				"u4",
				"a little energetic person",
				"Cfuser",
				"Four", 
				"Cfuser.Four@CampFire.project2",
				user4_bday,
				"cf4.pdf");
		userRepo.insert(user4);

		HashPassword hp = new HashPassword();
		String password=hp.hashPassword("cf5","cf5"); //String username, String password)
		Date user5_bday=new java.sql.Date(new java.util.Date().getTime());
		CF_User user5 = new CF_User(
				"cf5", 
				password, 
				"u5",
				"a little lethargic person",
				"Cfuser",
				"Five", 
				"Cfuser.Five@CampFire.project2",
				user5_bday,
				"cf5.pdf");
		userRepo.insert(user5);
		//public CF_Post(String cF_Post_CommentContents, Timestamp cF_Post_Created, String cF_Post_ImageURL,
		//		int cF_Post_AttachedTo, CF_User userHolderFromPost, List<CF_Like> likeList)
		java.util.Date now=new java.util.Date();
        //long time = now.getTime();
        //long timer = 3153273464000L;
        //Timestamp ts = new Timestamp(time);
		//java.sql.Date date=new java.sql.Date(now.getTime());
		CF_Post post1 = new CF_Post(
				"First Post",
				new Timestamp(now.getTime()),
				"firstpost.pdf",
				0,                                // cF_Post_AttachedTo = 0 (Post), 1(Comment)
				user4,
				new ArrayList<CF_Like>()
				);
		postRepo.insert(post1);
		CF_Post post2 = new CF_Post(
				"Comment to the FIrst Post",
				new Timestamp(now.getTime()),
				"comment_to_the_firstpost.pdf",
				1,                                // cF_Post_AttachedTo = 0 (Post), 1(Comment)
				user1,
				new ArrayList<CF_Like>()
				);
		postRepo.insert(post2);

		//CF_Like(Timestamp cF_Like_Created, CF_User userHolderFromLike, CF_Post postHolderFromLike)
		CF_Like like1 = new CF_Like(new Timestamp(new java.util.Date().getTime()), user2, post2);
		likeRepo.insert(like1);

	}
}
