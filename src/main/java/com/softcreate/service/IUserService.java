package com.softcreate.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.softcreate.model.User;

public interface IUserService extends UserDetailsService{

	boolean isLogin(HttpSession session);
	
	User login(User user);

	List<User> getPage(int pageNo, int pageSize);

	boolean addUser(User user);

	User getUser(Integer id);

	boolean deleteUser(User user);

	boolean updateUser(User user);

}
