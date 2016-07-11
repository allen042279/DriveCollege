package com.softcreate.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softcreate.dao.IUserDao;
import com.softcreate.model.User;

/*
 * @Scope指定此spring bean的scope是单例，你也可以根据需要将此bean指定为prototype
 * @Repository注解指定此类是一个容器类，是DAO层类的实现。
 * 
 */
@Scope("singleton")
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	@Transactional
	public User login(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getUsername());
		map.put("pwd", user.getPassword());
		return get(User.class, "From User as u Where u.username=:name and u.password=:pwd", map);
	}

	public List<User> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return findByPage("From User", pageNo, pageSize);
	}

	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return getById(User.class, id);
	}

	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return get(User.class, "From User as u Where u.username=:name", map);
	}
	
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			save(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		try {
			delete(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		try {
			update(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
