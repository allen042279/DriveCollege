package com.softcreate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface IBaseDao<T> {

	//根据指定的Hql语句获取实体
	T get(Class<T> entityClazz, String hql, Map<String, Object>params);
	
	//根据ID加载实体
	T getById(Class<T> entityClazz, Serializable id);
	
	//保存实体
	@Transactional Serializable save(T entity);
	
	//更新实体
	@Transactional void update(T entity);
	
	@Transactional void delete(T entity);
	
	//根据ID删除实体
	@Transactional void delete(Class<T> entityClazz, Serializable id);
	
	//获取所有实体
	List<T> findAll(Class<T> entityClazz);
	
	//获取实体总数
	long findCount(Class<T> entityClazz);
	
	// 根据HQL语句查询实体
	List<T> find(String hql);
	
	// 根据带占位符参数HQL语句查询实体
	List<T> find(String hql , Map<String, Object> params);
	
	//使用hql 语句进行分页查询操作
	List<T> findByPage(String hql, int pageNo, int pageSize);
	
	//使用hql 语句进行分页查询操作
	List<T> findByPage(String hql , int pageNo, int pageSize, Map<String, Object> params);
}
