package com.campfire.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Notes
// Hibernates important objects and interfaces

// Configuration (class)
//	Configuration's job is to gather information from the Hibernate Configuration file (hibernate.cfg.xml)
//	and use that information to create a session factory.

// SessionFactory (interface)
//  SessionFactory job is to create sessions and store information on how to make connections to your DB.
//  Once it is configured it is immutable.

// Session (interface)
//  Session manages the connection to your database and provides your CRUD methods (create, read, update, delete)

// Transaction (interface)
//  Transaction models a transaction during a session (and cache). Must be ACID.
//  Atomicity - Transactions are all or nothing, no partial Transactions.
//  Consistency - The schema remains intact after a transaction.
//  Isolation - keep transactions separate, they do not interfere with each other.
//  Durability - The data will persist through issues.


public class HibernateUtil {
	
	private static Session ses;
	
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		if(ses==null){
			ses = sf.openSession();
		}
		return ses;
	}
	
	public static void closeSes() {
		ses.close();
		sf.close();
	}

}

