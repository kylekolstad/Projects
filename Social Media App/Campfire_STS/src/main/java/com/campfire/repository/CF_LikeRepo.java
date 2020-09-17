package com.campfire.repository;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campfire.model.CF_Like;

@Transactional
@Repository("likeRepo")
public class CF_LikeRepo {
	
	private SessionFactory sesFact;
	
	public CF_LikeRepo() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CF_LikeRepo(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	public void insert(CF_Like like) {
		sesFact.getCurrentSession().save(like);
	}
	
	public List<CF_Like> selectAll(){
		return sesFact.getCurrentSession().createSQLQuery("Select * FROM CF_Like").list();
	}
	
	public void delete(String sql) {
		sesFact.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}