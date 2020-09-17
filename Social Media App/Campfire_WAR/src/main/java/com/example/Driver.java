package com.example;

import java.io.File;

import com.campfire.utilities.AWS;

public class Driver {
	
//	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
//	public static CF_PostRepo postRepo = appContext.getBean("postRepo", CF_PostRepo.class);
	
	public static void main(String[] args) {
		System.out.println("Hey");
		System.out.println(AWS.uploadToS3("Campfire/hairstyle.jpg", new File("C:/Users/gaylo/Documents/hairstyle.jpg")));
	}
	
	public static void insertDummyValues() {
		//CF_Post post1 = new Post("");
		//CF_UserRepo userRepo = new CF_UserRepo();
		
		//CF_User user1 = new CF_User("bobby123","password", "Construction Manager", "loves hunting and fishing", "Bobby","Jean", "bobbyboy@hotmail.com", new Date(123),"image.png");
		//userRepo.insert(user1);
		
		
	}
	
}

