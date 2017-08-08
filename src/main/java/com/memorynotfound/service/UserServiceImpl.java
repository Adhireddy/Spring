package com.memorynotfound.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.memorynotfound.dao.UserDAO;
import com.memorynotfound.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDAO userdao;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll(int offset, int count) {
    	List<User> users= userdao.getAll(offset, count);
    	return users;
    	
        
    }

    @Override
	public User findById(int id) {
		User user = userdao.findById(id);
		if (StringUtils.isEmpty(user)) {
			return null;
		}
		return user;
	}

	@Override
	public User findByName(String name) {
		User user = userdao.findByName(name);
		if (StringUtils.isEmpty(user)) {
			return null;
		}
		return user;
	}

    @Override
    public void create(User user) {
    	userdao.create(user);
    	
    }

    @Override
    public void update(User user) {
    	userdao.update(user);
    }

    @Override
    public void delete(int id) {
    	userdao.delete(id);
    	}

    @Override
    public boolean exists(User user) {
        return findByName(user.getUsername()) != null;
    }
}
