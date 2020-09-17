package com.campfire.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

//so far the plan for joins:
//user to hometown: one-to-one
//user to post: one-to-many
//user to likes: one-to-many (edited)

@Entity
@Table(name="CF_User")
public class CF_User implements Serializable {
	
	@Id
	@Column(name="CF_User_USerId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int CF_User_UserId;
	
	@Column(name="CF_User_Username", nullable = false, unique = true, length = 20)
	private String CF_User_Username;
	
	@Column(name="CF_User_Password", nullable = false, length = 100)
	private String CF_User_Password;
	
	@Column(name="CF_User_Title", nullable = true)
	private String CF_User_Title;
	
	@Column(name="CF_User_Bio", nullable = true, length = 1000)
	private String CF_User_Bio;
	
	@Column(name="CF_User_Fname", nullable = false)
	private String CF_User_Fname;
	
	@Column(name="CF_User_Lname", nullable = false)
	private String CF_User_Lname;
	
	@Column(name="CF_User_Email", nullable = false, unique = true, length = 50)
	private String CF_User_Email;
	
	@Column(name="CF_User_Birthday", nullable = true)
	private Date CF_User_Birthday;
	
	//@Column(name="CF_User_ZipCodeId", nullable = false)
	//private int CF_User_ZipCodeId;
	
	@Column(name="CF_User_ProfilePic", nullable = true, length = 2000)
	private String CF_User_ProfilePic;
	
//	@OneToOne
//	@JoinColumn(name="CF_User_ZipCodeId")
//	private CF_Hometown hometown;
	@JsonBackReference
	@OneToMany(mappedBy="userHolderFromPost", fetch=FetchType.EAGER)
	private List<CF_Post> postList = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy="userHolderFromLike", fetch=FetchType.EAGER)
	private List<CF_Like> likeList = new ArrayList<>();

	// username and password and email constructor
	public CF_User(String cF_User_Username, String cF_User_Password, String cF_User_Email) {
		super();
		CF_User_Username = cF_User_Username;
		CF_User_Password = cF_User_Password;
		CF_User_Email = cF_User_Email;
	}
	
	public CF_User(String cF_User_Username, String cF_User_Password, String cF_User_Title, String cF_User_Bio,
			String cF_User_Fname, String cF_User_Lname, String cF_User_Email, Date cF_User_Birthday,
			String cF_User_ProfilePic) {
		super();
		CF_User_Username = cF_User_Username;
		CF_User_Password = cF_User_Password;
		CF_User_Title = cF_User_Title;
		CF_User_Bio = cF_User_Bio;
		CF_User_Fname = cF_User_Fname;
		CF_User_Lname = cF_User_Lname;
		CF_User_Email = cF_User_Email;
		CF_User_Birthday = cF_User_Birthday;
		CF_User_ProfilePic = cF_User_ProfilePic;
	}

	// No Args Constructor
	public CF_User() {
		// TODO Auto-generated constructor stub
	}

	 //Full Constructor
	public CF_User(int cF_User_UserId, String cF_User_Username, String cF_User_Password, String cF_User_Title,
			String cF_User_Bio, String cF_User_Fname, String cF_User_Lname, String cF_User_Email, Date cF_User_Birthday,
			String cF_User_ProfilePic, List<CF_Post> postList, List<CF_Like> likeList) {
		super();
		CF_User_UserId = cF_User_UserId;
		CF_User_Username = cF_User_Username;
		CF_User_Password = cF_User_Password;
		CF_User_Title = cF_User_Title;
		CF_User_Bio = cF_User_Bio;
		CF_User_Fname = cF_User_Fname;
		CF_User_Lname = cF_User_Lname;
		CF_User_Email = cF_User_Email;
		CF_User_Birthday = cF_User_Birthday;
		CF_User_ProfilePic = cF_User_ProfilePic;
		this.postList = postList;
		this.likeList = likeList;
	}

	public int getCF_User_UserId() {
		return CF_User_UserId;
	}

	public void setCF_User_UserId(int cF_User_UserId) {
		CF_User_UserId = cF_User_UserId;
	}

	public String getCF_User_Username() {
		return CF_User_Username;
	}

	public void setCF_User_Username(String cF_User_Username) {
		CF_User_Username = cF_User_Username;
	}

	public String getCF_User_Password() {
		return CF_User_Password;
	}

	public void setCF_User_Password(String cF_User_Password) {
		CF_User_Password = cF_User_Password;
	}

	public String getCF_User_Title() {
		return CF_User_Title;
	}

	public void setCF_User_Title(String cF_User_Title) {
		CF_User_Title = cF_User_Title;
	}

	public String getCF_User_Bio() {
		return CF_User_Bio;
	}

	public void setCF_User_Bio(String cF_User_Bio) {
		CF_User_Bio = cF_User_Bio;
	}

	public String getCF_User_Fname() {
		return CF_User_Fname;
	}

	public void setCF_User_Fname(String cF_User_Fname) {
		CF_User_Fname = cF_User_Fname;
	}

	public String getCF_User_Lname() {
		return CF_User_Lname;
	}

	public void setCF_User_Lname(String cF_User_Lname) {
		CF_User_Lname = cF_User_Lname;
	}

	public String getCF_User_Email() {
		return CF_User_Email;
	}

	public void setCF_User_Email(String cF_User_Email) {
		CF_User_Email = cF_User_Email;
	}

	public Date getCF_User_Birthday() {
		return CF_User_Birthday;
	}

	public void setCF_User_Birthday(Date cF_User_Birthday) {
		CF_User_Birthday = cF_User_Birthday;
	}

	public String getCF_User_ProfilePic() {
		return CF_User_ProfilePic;
	}

	public void setCF_User_ProfilePic(String cF_User_ProfilePic) {
		CF_User_ProfilePic = cF_User_ProfilePic;
	}

	public List<CF_Post> getPostList() {
		return postList;
	}

	public void setPostList(List<CF_Post> postList) {
		this.postList = postList;
	}

	public List<CF_Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<CF_Like> likeList) {
		this.likeList = likeList;
	}

	@Override
	public String toString() {
		return "CF_User [CF_User_UserId=" + CF_User_UserId + ", CF_User_Username=" + CF_User_Username
				+ ", CF_User_Password=" + CF_User_Password + ", CF_User_Title=" + CF_User_Title + ", CF_User_Bio="
				+ CF_User_Bio + ", CF_User_Fname=" + CF_User_Fname + ", CF_User_Lname=" + CF_User_Lname
				+ ", CF_User_Email=" + CF_User_Email + ", CF_User_Birthday=" + CF_User_Birthday
				+ ", CF_User_ProfilePic=" + CF_User_ProfilePic + "]";
	}

}
