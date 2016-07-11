package com.softcreate.dao.daoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.softcreate.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T>{
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public T get(Class<T> entityClazz, String hql,  Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<T> resultList = find(hql, params);
		
		return resultList.isEmpty()?null:resultList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public T getById(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		return (T)sessionFactory.getCurrentSession().get(entityClazz , id);
	}

	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	public Serializable save(T entity) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().save(entity);
	}

	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	public void update(T entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	public int updateSql(String hqlString, Map<String, Object> params) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().
		// 创建查询
		Query query = sessionFactory.getCurrentSession().createQuery(hqlString);
		
		// 为包含占位符的HQL语句设置参数
		if (params != null && !params.isEmpty()) {         
			for (String key : params.keySet()) {             
				query.setParameter(key, params.get(key));  
			}     
		} 
		
		return query.executeUpdate();
	}
	
	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	public void delete(T entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	/**
	 * 使用注解表明这个方式使用声明式事务
	 */
	public void delete(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("delete " + entityClazz.getSimpleName()
			                                         + " en where en.id = ?0 ")
		.setParameter("0" , id)
		.executeUpdate();
	}

	public List<T> findAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return find("select en from " + entityClazz.getSimpleName() + " en ");
	}

	public long findCount(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		List<?> l = find("select count(*) from " + entityClazz.getSimpleName());
		
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1 ){
			return (Long)l.get(0);
		}
		
		return 0;
	}
	
	// 根据HQL语句查询实体
	@SuppressWarnings("unchecked")
	public List<T> find(String hql){
		
		return (List<T>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	// 根据带占位符参数HQL语句查询实体
	@SuppressWarnings("unchecked")
	public List<T> find(String hql , Map<String, Object> params) {
		
		// 创建查询
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		// 为包含占位符的HQL语句设置参数
		if (params != null && !params.isEmpty()) {         
			for (String key : params.keySet()) {             
				query.setParameter(key, params.get(key));         
			}     
		} 

		return (List<T>)query.list();
	}
	
	// 根据带占位符参数HQL语句查询实体
//	public List find2(String hql , Map<String, Object> params) {
//		
//		// 创建查询
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		
//		// 为包含占位符的HQL语句设置参数
//		if (params != null && !params.isEmpty()) {         
//			for (String key : params.keySet()) {             
//				query.setParameter(key, params.get(key));         
//			}     
//		} 
//
//		return query.list();
//	}
	
	/**
	 * 使用hql 语句进行分页查询操作
	 * @param hql 需要查询的hql语句
	 * @param pageNo 查询第pageNo页的记录
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int pageNo, int pageSize){
		
		// 创建查询
		return sessionFactory.getCurrentSession()
			.createQuery(hql)
			// 执行分页
			.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
	
	/**
	 * 使用hql 语句进行分页查询操作
	 * @param hql 需要查询的hql语句
	 * @param params 如果hql带占位符参数，params用于传入占位符参数
	 * @param pageNo 查询第pageNo页的记录
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql , int pageNo, int pageSize, Map<String, Object> params)	{
		
		// 创建查询
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		// 为包含占位符的HQL语句设置参数
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {             
				query.setParameter(key, params.get(key));         
			}     
		} 
		
		// 执行分页，并返回查询结果
		return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

//	public List findByPage2(String hql , int pageNo, int pageSize, Map<String, Object> params)	{
//		
//		// 创建查询
//		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
//		
//		// 为包含占位符的HQL语句设置参数
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) { 
//				query.setParameter(key, params.get(key)); 
//			}     
//		} 
//		
//		// 执行分页，并返回查询结果
//		return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
//	}
}
