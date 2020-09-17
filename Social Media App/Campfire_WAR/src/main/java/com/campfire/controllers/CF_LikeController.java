package com.campfire.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campfire.model.CF_Like;
import com.campfire.model.CF_Post;
import com.campfire.model.CF_User;
import com.campfire.repository.CF_LikeRepo;
import com.campfire.repository.CF_PostRepo;
import com.campfire.repository.CF_UserRepo;

@Controller
public class CF_LikeController {

	private CF_LikeRepo likeRepo;
	private CF_PostRepo postRepo;
	private CF_UserRepo userRepo;
	
	public CF_LikeController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CF_LikeController(CF_LikeRepo likeRepo, CF_PostRepo postRepo, CF_UserRepo userRepo) {
		super();
		this.likeRepo = likeRepo;
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}
	
	@PostMapping(value="/insertLike.app", produces="application/json")
	public @ResponseBody int insert(@RequestParam("creationtime") String creationtime,@RequestParam("postid") String postid,@RequestParam("userid") String userid) {
		CF_User uso = userRepo.selectById((Integer.parseInt(userid)));
		CF_Post posto = postRepo.selectById(Integer.parseInt(postid));
		CF_Like liko = new CF_Like(new Timestamp(System.currentTimeMillis()), uso, posto);
		likeRepo.insert(liko);
		return 5;
	}
	
	@PostMapping(value="/deleteLike.app", produces="application/json")
	public void delete(@RequestParam("postid") int postid, @RequestParam("userid") int userid) {
		System.out.println(postid);
		System.out.println(userid);
		String sqlstring = "DELETE from CF_Like where CF_User_UserId = " + (userid) + " and CF_Post_PostId = " + (postid);
		likeRepo.delete(sqlstring);
	}
	
	@GetMapping(value="/getAllLikes.app", produces="application/json")
	public @ResponseBody List<CF_Like> getAllPosts() {
		return likeRepo.selectAll();
	}	
}