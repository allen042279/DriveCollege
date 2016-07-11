package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.PreStudent;

public interface IPreStudentDao {

	long count();
	List<PreStudent> getAll(String fromDate, String toDate);
	List<PreStudent> getPage(int pageNo, int pageSize);
	PreStudent getPreStudent(int id);
	
	boolean savePreStudent(PreStudent preStudent);
	boolean updatePreStudent(PreStudent preStudent);
	boolean deletePreStudent(PreStudent preStudent);

}
