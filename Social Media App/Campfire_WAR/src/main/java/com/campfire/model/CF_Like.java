package com.campfire.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//so far the plan for joins:
//likes to post: many-to-one
//likes to user: many-to-one 

@Entity
@Table(name="CF_Like")
public class CF_Like implements Serializable {
	
//	@Id
//	@Column(name="CF_User_UserId", nullable = false)
//	private int CF_User_UserId;
	
//	@Id
//	@Column(name="CF_Post_PostId", nullable = false)
//	private int CF_Post_PostId;
	
	@Column(name="CF_Like_Created", nullable = true)
	private Timestamp CF_Like_Created;
	
	@JsonManagedReference
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="CF_User_UserId")
	private CF_User userHolderFromLike;
	
	@JsonManagedReference
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="CF_Post_PostId")
	private CF_Post postHolderFromLike;
	

	// Full Constructor
	public CF_Like(Timestamp cF_Like_Created, CF_User userHolderFromLike, CF_Post postHolderFromLike) {
		super();
		CF_Like_Created = cF_Like_Created;
		this.userHolderFromLike = userHolderFromLike;
		this.postHolderFromLike = postHolderFromLike;
	}

	public CF_Like() {
		super();
	}

	public CF_User getUserHolderFromLike() {
		return userHolderFromLike;
	}

	public void setUserHolderFromLike(CF_User userHolderFromLike) {
		this.userHolderFromLike = userHolderFromLike;
	}

	public CF_Post getPostHolderFromLike() {
		return postHolderFromLike;
	}

	public void setPostHolderFromLike(CF_Post postHolderFromLike) {
		this.postHolderFromLike = postHolderFromLike;
	}

	public Timestamp getCF_Like_Created() {
		return CF_Like_Created;
	}

	public void setCF_Like_Created(Timestamp cF_Like_Created) {
		CF_Like_Created = cF_Like_Created;
	}
	
	@Override
	public String toString() {
		return "CF_Like [CF_Like_Created=" + CF_Like_Created + ", userHolderFromLike=" + userHolderFromLike
				+ ", postHolderFromLike=" + postHolderFromLike + "]";
	}
	
}
