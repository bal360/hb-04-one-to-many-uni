package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;

public class DeleteCourseAndReviewsDemo {
	
	public static void main(String[] args) {
		
		// create factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// begin transaction
			session.beginTransaction();
			
			// create id and get course by id
			int id = 10;
			
			Course course = session.get(Course.class, id);
			
			// delete the course AND associated reviews with cascade
			session.delete(course);
			
			// commit the transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			
			factory.close();
		}
	}

}
