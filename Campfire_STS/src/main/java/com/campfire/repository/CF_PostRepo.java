package com.campfire.repository;

import java.util.List;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campfire.model.CF_Post;

@Transactional
@Repository("postRepo")
public class CF_PostRepo {
	
	private SessionFactory sesFact;
	
	// No Args Constructor
	public CF_PostRepo() {
	}
	
	@Autowired
	public CF_PostRepo(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	public void insert(CF_Post post) {
		sesFact.getCurrentSession().save(post);
	}
	
	public CF_Post selectById(int id) {
		return sesFact.getCurrentSession().get(CF_Post.class, id);
	}
	
	public List<CF_Post> selectAll(){
		return sesFact.getCurrentSession().createSQLQuery("Select u.cf_user_userid,u.cf_user_fname,u.cf_user_lname,u.cf_user_profilepic,u.cf_user_title, p.cf_post_post_id,p.cf_post_commentcontents,p.cf_post_created,p.cf_post_imageurl, count(L.CF_LIKE_CREATED) from CF_Post p INNER JOIN CF_User u ON p.cf_user_userid = u.cf_user_userid LEFT OUTER JOIN CF_Like l on p.cf_post_post_id = l.cf_post_postid GROUP BY u.cf_user_userid,u.cf_user_fname,u.cf_user_lname,u.cf_user_profilepic,u.cf_user_title, p.cf_post_post_id,p.cf_post_commentcontents,p.cf_post_created,p.cf_post_imageurl ORDER BY p.cf_post_post_id DESC").getResultList();
	}

}