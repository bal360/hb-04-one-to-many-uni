package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;

public class CreateCourseAndReviewsDemo {

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
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course course = new Course("ENG 4302 - Joy Williams");
			
			// add some reviews
			course.addReview(new Review("Best course I've taken yet"));
			course.addReview(new Review("Instructor was phenomenal"));
			course.addReview(new Review("Why does any read this author?! Terrible teacher, terrible author"));
			
			//save the course ... and leverage the cascade all
			session.save(course);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
