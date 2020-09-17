package com.campfire.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//so far the plan for joins:
//post to user: many-to-one
//post to likes: one-to-many

@Entity
@Table(name = "CF_Post")
public class CF_Post  implements Serializable {

	@Id
	@Column(name = "CF_Post_Post_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int CF_Post_PostId;

	@Column(name = "CF_Post_CommentContents", nullable = true, length = 500)
	private String CF_Post_CommentContents;

	@Column(name = "CF_Post_Created", nullable = false)
	private Timestamp CF_Post_Created;

	@Column(name = "CF_Post_ImageURL", length = 2000)
	private String CF_Post_ImageURL;

	@Column(name = "CF_Post_AttachedTo")
	private int CF_Post_AttachedTo;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CF_User_UserId", nullable = false)
	private CF_User userHolderFromPost;

	@JsonBackReference
	@OneToMany(mappedBy = "postHolderFromLike", fetch = FetchType.EAGER)
	private List<CF_Like> likeList = new ArrayList<>();

	// Full Constructor
	public CF_Post(int cF_Post_PostId, String cF_Post_CommentContents, Timestamp cF_Post_Created,
			String cF_Post_ImageURL, int cF_Post_AttachedTo, CF_User userHolderFromPost, List<CF_Like> likeList) {
		super();
		CF_Post_PostId = cF_Post_PostId;
		CF_Post_CommentContents = cF_Post_CommentContents;
		CF_Post_Created = cF_Post_Created;
		CF_Post_ImageURL = cF_Post_ImageURL;
		CF_Post_AttachedTo = cF_Post_AttachedTo;
		this.userHolderFromPost = userHolderFromPost;
		this.likeList = likeList;
	}

	// No ID Constructor
	public CF_Post(String cF_Post_CommentContents, Timestamp cF_Post_Created, String cF_Post_ImageURL,
			int cF_Post_AttachedTo, CF_User userHolderFromPost, List<CF_Like> likeList) {
		super();
		CF_Post_CommentContents = cF_Post_CommentContents;
		CF_Post_Created = cF_Post_Created;
		CF_Post_ImageURL = cF_Post_ImageURL;
		CF_Post_AttachedTo = cF_Post_AttachedTo;
		this.userHolderFromPost = userHolderFromPost;
		this.likeList = likeList;
	}
	
	// NO attached to value.
		public CF_Post(String cF_Post_CommentContents, Timestamp cF_Post_Created, String cF_Post_ImageURL,
				CF_User userHolderFromPost, List<CF_Like> likeList) {
			super();
			CF_Post_CommentContents = cF_Post_CommentContents;
			CF_Post_Created = cF_Post_Created;
			CF_Post_ImageURL = cF_Post_ImageURL;
			this.userHolderFromPost = userHolderFromPost;
			this.likeList = likeList;
		}

	// No Args Constructor
	public CF_Post() {
		// TODO Auto-generated constructor stub
	}

	public int getCF_Post_PostId() {
		return CF_Post_PostId;
	}

	public void setCF_Post_PostId(int cF_Post_PostId) {
		CF_Post_PostId = cF_Post_PostId;
	}

	public String getCF_Post_CommentContents() {
		return CF_Post_CommentContents;
	}

	public void setCF_Post_CommentContents(String cF_Post_CommentContents) {
		CF_Post_CommentContents = cF_Post_CommentContents;
	}

	public Timestamp getCF_Post_Created() {
		return CF_Post_Created;
	}

	public void setCF_Post_Created(Timestamp cF_Post_Created) {
		CF_Post_Created = cF_Post_Created;
	}

	public String getCF_Post_ImageURL() {
		return CF_Post_ImageURL;
	}

	public void setCF_Post_ImageURL(String cF_Post_ImageURL) {
		CF_Post_ImageURL = cF_Post_ImageURL;
	}

	public int getCF_Post_AttachedTo() {
		return CF_Post_AttachedTo;
	}

	public void setCF_Post_AttachedTo(int cF_Post_AttachedTo) {
		CF_Post_AttachedTo = cF_Post_AttachedTo;
	}

	public CF_User getUserHolderFromPost() {
		return userHolderFromPost;
	}

	public void setUserHolderFromPost(CF_User userHolderFromPost) {
		this.userHolderFromPost = userHolderFromPost;
	}

	public List<CF_Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<CF_Like> likeList) {
		this.likeList = likeList;
	}

	@Override
	public String toString() {
		return "CF_Post [CF_Post_PostId=" + CF_Post_PostId + ", CF_Post_CommentContents=" + CF_Post_CommentContents
				+ ", CF_Post_Created=" + CF_Post_Created + ", CF_Post_ImageURL=" + CF_Post_ImageURL
				+ ", CF_Post_AttachedTo=" + CF_Post_AttachedTo + ", userHolderFromPost=" + userHolderFromPost + "]";
	}
	
}