package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.User;

public interface IUserDao {

	public User login(User user);

	
	public List<User> getPage(int pageNo, int pageSize);

	public User getUser(Integer id);
	
	public User getUserByName(String name);
	
	public boolean addUser(User user);

	public boolean deleteUser(User user);

	public boolean updateUser(User user);
	
}
