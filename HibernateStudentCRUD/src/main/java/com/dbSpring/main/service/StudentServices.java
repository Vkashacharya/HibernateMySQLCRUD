package com.dbSpring.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.dbSpring.main.model.StudentModel;

@Component
@EnableAsync
@EnableTransactionManagement
@Scope(scopeName = "prototype")
@Transactional

public class StudentServices {
	
	@Autowired
	private SessionFactory sessionFactory;

//	public Session getSession() {
//		return entityManagerFactory.unwrap(Session.class);
//	}
	
	public List<StudentModel> getAllStudent(){
		
		try {
			final Session session = sessionFactory.getCurrentSession();
			TypedQuery<StudentModel> query = session.createQuery("FROM "+StudentModel.class.getName(),StudentModel.class);
			ArrayList<StudentModel> list = (ArrayList<StudentModel>) query.getResultList();
			
			return list; 
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public StudentModel save(StudentModel model) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(model);
		session.flush();
		session.clear();
		return model;
	}

	public void deleteById(String id) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete((StudentModel) session.load(StudentModel.class, id));
		session.flush();
		session.clear();
	}

	public StudentModel getStudentsById(String id) {
		final Session session = sessionFactory.getCurrentSession();
		return (StudentModel) session.load(StudentModel.class, id);
	}

	public void update(StudentModel returnModel) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(returnModel);
		session.flush();
		session.clear();
	}

}
