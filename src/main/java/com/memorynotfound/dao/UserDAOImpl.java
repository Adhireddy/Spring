package com.memorynotfound.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.memorynotfound.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl() {
		System.out.println("UserDAOImpl..constructed...");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll(int offset, int count) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> countryList = session.createQuery("from User").list();
		  return countryList;
		
	}

	@Override
	public User findById(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		  return user;
	}

	@Override
	public User findByName(String name) {
		
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, name);
		  return user;
		
	
	}

	@Override
	public void create(User user) {
		
		Session session = this.sessionFactory.getCurrentSession();
		  session.persist(user);
	}

	@Override
	public void update(User user) {
		
		Session session = this.sessionFactory.getCurrentSession();
		  session.update(user);
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		  User user = (User) session.load(User.class, new Integer(id));
		  if (null != user) {
		   session.delete(user);
		  }
	}

	@Override
	public boolean exists(User user) {
		return findByName(user.getUsername()) != null;
	}
}
