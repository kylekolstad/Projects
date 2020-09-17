package com.campfire.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campfire.model.CF_User;
import com.campfire.utilities.HashPassword;

@Transactional
@Repository("userRepo")
public class CF_UserRepo {

	private SessionFactory sesFact;

	public CF_UserRepo() {
	}

	@Autowired
	public CF_UserRepo(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	public int insert(CF_User user) {
		CF_User existingUname = selectByUsername(user.getCF_User_Username());
		CF_User existingEmail = selectByEmail(user.getCF_User_Email());

		int results = 0;

		/*
		 * System.out.println(existingUname); System.out.println(existingEmail);
		 */

		if ((existingUname !=null) && (existingEmail != null)) {
			return -3;
		} else if (existingUname !=null) {
			return -2;
		} else if (existingEmail != null) {
			return -1;
		} else {
			results = (Integer) sesFact.getCurrentSession().save(user);
			System.out.println("Results: " + results);
			return results;
		}
	}

	public CF_User selectById(int id) {
		return sesFact.getCurrentSession().get(CF_User.class, id);
	}

	public CF_User selectByUsername(String username) {
		try {CF_User cf_user = sesFact.getCurrentSession()
					.createQuery("from CF_User WHERE CF_User_Username = '" + username.toLowerCase() + "'",
							CF_User.class)
					.getSingleResult();
			return cf_user;
		} catch (NoResultException e) {
			return null;
		}
	}

	public CF_User selectByEmail(String useremail) {
		try {CF_User cf_user = sesFact.getCurrentSession()
				.createQuery("from CF_User WHERE CF_User_Email = '" + useremail.toLowerCase() + "'", CF_User.class)
				.getSingleResult();
		return cf_user; }
		catch (NoResultException e) {
			return null;
		}
	}

	public List<CF_User> selectAll() {
		System.out.println("I'm in SelectAll (USER REPO)");
		return sesFact.getCurrentSession().createQuery("from CF_User", CF_User.class).list();
	}

	public CF_User selectByUnamePword(String uname, String pword) {
		System.out.println("I'm in Select By Username and Password (USER REPO)");

		HashPassword hp = new HashPassword();
		String hashedPassword = hp.hashPassword(uname.toLowerCase(), pword);

		CF_User cf_user;
		try {
			cf_user = sesFact.getCurrentSession()
					.createQuery("from CF_User WHERE CF_User_Username = '" + uname.toLowerCase() + "'", CF_User.class)
					.getSingleResult();

			String userpassword = cf_user.getCF_User_Password();

			System.out.println("Password from Database: " + userpassword);
			System.out.println("Password from Hash Function: " + hashedPassword);
			if (!hashedPassword.equals(userpassword)) {
				cf_user = null;
			}
			
			return cf_user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	//1) update bio
		public String updateUserBio(String uname, String bio) {
	        CF_User existingUser = selectByUsername(uname);
	        
	        if ( existingUser == null ) {
	            System.out.println("DEBUG the username = "+existingUser+" does not exist");
	        }
	        
	        existingUser.setCF_User_Bio(bio);
	        sesFact.getCurrentSession().update(existingUser);
	        
	        return existingUser.getCF_User_Bio();
	    }
		
		//2) update title
		public String updateUserTitle(String uname, String title) {
	        CF_User existingUser = selectByUsername(uname);
	        
	        if ( existingUser == null ) {
	            System.out.println("DEBUG the username = "+existingUser+" does not exist");
	        }
	        
	        existingUser.setCF_User_Title(title);
	        sesFact.getCurrentSession().update(existingUser);
	        
	        return existingUser.getCF_User_Title();
	    }
		
		//3) update picture
		public String updateUserProfilePic(String uname, String pic_url) {
	        CF_User existingUser = selectByUsername(uname);
	        
	        if ( existingUser == null ) {
	            System.out.println("DEBUG the username = "+existingUser+" does not exist");
	        }
	        
	        existingUser.setCF_User_ProfilePic(pic_url);;
	        sesFact.getCurrentSession().update(existingUser);
	        
	        return existingUser.getCF_User_ProfilePic();
	    }
		


		public CF_User checkUnameEmail(String uname, String email) {
			CF_User existingUser_1 = selectByUsername(uname);
			CF_User existingUser_2 = selectByEmail(email);
			
			System.out.println("By Username" + existingUser_1);
			System.out.println("By email" + existingUser_1);
			
			if (existingUser_1 == null) {
				return null;
			}
			else if (existingUser_2 == null) {
				return null;
			}
			else if ((existingUser_1.getCF_User_Username() != existingUser_2.getCF_User_Username())) {
				return null;
			}
			else {
				return existingUser_1;
			}
		}
		
		public int updateUserPassword(CF_User user, String password) {
	        //CF_User existingUser = selectByUsername(uname);
	        
	        String hashedPassword = HashPassword.hashPassword(user.getCF_User_Username().toLowerCase(), password);
	        
	        user.setCF_User_Password(hashedPassword);
	        
	        sesFact.getCurrentSession().update(user);
	        
	        return 1;
	    }
			
	

}
