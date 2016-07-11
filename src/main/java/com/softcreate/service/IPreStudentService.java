package com.softcreate.service;

import java.util.List;

import com.softcreate.model.PreStudent;

public interface IPreStudentService {

	List<PreStudent> getAll(String fromDate, String toDate);
	
//	@RolesAllowed("ROLE_ADMIN")
	List<PreStudent> getPage(int page, int pageSize);
	
//	@RolesAllowed("ROLE_ADMIN")
	PreStudent getPreStudent(int id);
	
//	@RolesAllowed("ROLE_ADMIN")
	long count();
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean addPreStudent(PreStudent preStudent);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean deletePreStudent(PreStudent preStudent);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean updatePreStudent(PreStudent preStudent);
	
}
