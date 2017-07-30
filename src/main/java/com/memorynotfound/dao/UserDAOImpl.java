package com.memorynotfound.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl() {
		System.out.println("UserDAOImpl..constructed...");
	}


	@Override
	public List<User> getAll(int offset, int count) {
		String sql = "SELECT * FROM user";
		List<User> listContact = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));

				return user;
			}

		});

		return listContact;
	}

	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM user WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setAge(rs.getInt("age"));
					return user;
				}

				return null;
			}

		});
	}

	@Override
	public User findByName(String name) {
		String sql = "SELECT * FROM user WHERE username=" + name;
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setAge(rs.getInt("age"));
					return user;
				}

				return null;
			}

		});
	}

	@Override
	public void create(User user) {
		String sql = "INSERT INTO user (username, email, age,id)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getAge(), user.getId());

	}

	@Override
	public void update(User user) {
		String sql = "UPDATE user SET username=?, email=?, age=? WHERE id=?";
		jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getAge(), user.getId());

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM user WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public boolean exists(User user) {
		return findByName(user.getUsername()) != null;
	}
}
