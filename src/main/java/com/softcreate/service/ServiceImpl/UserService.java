package com.softcreate.service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softcreate.dao.IUserDao;
import com.softcreate.model.User;
import com.softcreate.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

	@Resource private IUserDao userDao;
	
	//判断session是否为空(判断用户是否空)
	public boolean isLogin(HttpSession session) {	
		
		User userSession = (User) session.getAttribute("user");
		if (userSession == null) {
			return false;
		}
		return true;
	}
	
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.getUserByName(userName);      // 根据username来得到User对象
        return (UserDetails) user;
	}

	public List<User> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.getPage(pageNo, pageSize);
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(user);
	}

	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

}
