package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;

public class GetCourseAndReviewsDemo {
	
	public static void main(String[] args) {
		
		// create factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// begin transaction
			session.beginTransaction();
			
			// create id to find course
			int id = 10;
			
			
			// get course AND associated reviews
			Course course = session.get(Course.class,  id);
			
			System.out.println("Course: " + course.getTitle());
			
//			System.out.println("Course Reviews: " + course.getReviews());
			for (Review review : course.getReviews()) {
				System.out.println("Review: " + review.getComment());
			}
			
			// commit the transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			
			factory.close();
		}
	}
}
