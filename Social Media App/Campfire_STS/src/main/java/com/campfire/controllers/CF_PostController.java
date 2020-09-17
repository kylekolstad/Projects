package com.campfire.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.campfire.model.CF_Post;
import com.campfire.model.CF_User;
import com.campfire.repository.CF_PostRepo;
import com.campfire.repository.CF_UserRepo;
import com.campfire.utilities.AWS;

@Controller
public class CF_PostController {

	private CF_PostRepo postRepo;
	private CF_UserRepo userRepo;

	public CF_PostController() {
	}

	@Autowired
	public CF_PostController(CF_PostRepo postRepo, CF_UserRepo userRepo) {
		super();
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}

	@GetMapping(value = "/getAllPosts.app", produces = "application/json")
	public @ResponseBody List<CF_Post> getAllPosts() {
		return postRepo.selectAll();
	}

	@PostMapping(value = "/insertPost.app", produces = "application/json", consumes = {"multipart/form-data"})
	public @ResponseBody int createPost(@RequestParam("postcontents") String contents,
			@RequestParam("userid") int userid, @RequestParam(value = "file", required = false) MultipartFile file)
			throws IOException {
		CF_User user = userRepo.selectById(userid);
		System.out.println("I'm in insertPost");
		System.out.println(file);
		if (file != null) {
			CF_Post post = new CF_Post(contents, new Timestamp(System.currentTimeMillis()),
					AWS.uploadToS3Inputsream(
							String.valueOf(userid) + new Timestamp(System.currentTimeMillis()) + ".jpg", file),
					user, null);
			postRepo.insert(post);
		} else {
			CF_Post post = new CF_Post(contents, new Timestamp(System.currentTimeMillis()), "", user, null);
			postRepo.insert(post);	
		}
		return 1;
	}
}