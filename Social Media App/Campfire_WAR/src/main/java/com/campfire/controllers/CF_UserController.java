package com.campfire.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.campfire.model.CF_User;
import com.campfire.repository.CF_UserRepo;
import com.campfire.utilities.AWS;
import com.campfire.utilities.EmailAPI;
import com.campfire.utilities.GeneratePassword;
import com.campfire.utilities.HashPassword;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true")
@Controller
public class CF_UserController {

	private CF_UserRepo userRepo;

	public CF_UserController() {
	}

	@Autowired
	public CF_UserController(CF_UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@PostMapping(value="/getAllUsers.app", produces="application/json")
	public @ResponseBody List<CF_User> getAllUsers(HttpSession session) {
		return userRepo.selectAll();
	}
	
	@PostMapping(value="/getUserById.app", produces="application/json")
	public @ResponseBody CF_User selectById(HttpSession session, @RequestParam("Id") int num) {
		return userRepo.selectById(num);
	}
	
	@PostMapping(value="/getUserByUsername.app", produces="application/json")
	public @ResponseBody CF_User selectByUsername(HttpSession session, @RequestParam("username") String username) {
		return userRepo.selectByUsername(username);
	}
	
	@PostMapping(value="/getUserByEmail.app", produces="application/json")
	public @ResponseBody CF_User selectByEmail(HttpSession session, @RequestParam("useremail") String useremail) {
		return userRepo.selectByEmail(useremail);
	}
	
	@PostMapping(value="/getUserByNamePassword.app", produces="application/json")
	public @ResponseBody CF_User selectByUnamePword(HttpServletRequest req, @RequestParam("username") String uname, @RequestParam("password") String pword) {
		HttpSession session = req.getSession();
		CF_User cf_user;
		cf_user = userRepo.selectByUnamePword(uname, pword);
		if(cf_user != null) {
			System.out.println(cf_user);
			session.setAttribute("loggeduser", cf_user);
			System.out.println("In getUserByNamePassword: " + session.getAttribute("loggeduser"));
			
		}
		return cf_user;
	}
	
	@PostMapping(value="/createUser.app", produces="application/json")
	public @ResponseBody int createUser(@RequestParam("userbio") String userbio, @RequestParam("userbirthday") String userbirthday, @RequestParam("useremail") String useremail, @RequestParam("userfname") String userfname, @RequestParam("userlname") String userlname, @RequestParam("userpassword") String userpassword, @RequestParam("userprofilepic") String userprofilepic, @RequestParam("usertitle") String usertitle,  @RequestParam("username") String username) {
		return userRepo.insert(new CF_User(username.toLowerCase(), userpassword, usertitle, userbio, userfname, userlname, useremail.toLowerCase(), Date.valueOf(userbirthday), userprofilepic));
	}
	
	@PostMapping(value = "/updateTitleBioProfile.app", consumes = {"multipart/form-data"})
	public @ResponseBody String updateTitleBioProfile(HttpSession session, @RequestParam("username") String username, @RequestParam("cf_User_Title") String title, @RequestParam("cf_User_Bio") String bio, @RequestParam("cf_User_ProfilePic") MultipartFile file) throws IOException  {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		CF_User currUser = (CF_User) session.getAttribute("loggeduser");
		
		System.out.println(currUser);
		
		if (bio != null) {
			userRepo.updateUserBio(username, bio);
		} 
		
		if (title != null) {
			userRepo.updateUserTitle(username, title);
		}
		
		if (file != null) {
			userRepo.updateUserProfilePic(username, AWS.uploadToS3Inputsream(username + timestamp + ".jpg", file));
		}
		
		return null;
	}
	
	@GetMapping(value = "/logout.app")
	public @ResponseBody void logout(HttpSession session) {
		System.out.println("in the logout method");
		session.invalidate();

		System.out.println("\n\n\n");
		//return ("You are now logged out");
	}
	
	@PostMapping(value = "/updatePassword.app", produces="application/json")
	public @ResponseBody int updatePassword(HttpSession session, @RequestParam("username") String username, @RequestParam("email") String email) throws IOException  {
		System.out.println(username);
		System.out.println(email);
		CF_User someUser = userRepo.checkUnameEmail(username, email);
		
		String randomPassword = "";
			
		if (someUser == null) {
			return -2;
		}
		else { 
			randomPassword = GeneratePassword.generateRandomPassword();
			userRepo.updateUserPassword(someUser, randomPassword);
			EmailAPI.sendEmail(someUser.getCF_User_Fname(), email, randomPassword);
			
			return 1;
		}

	}

}
