package com.softcreate.service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.softcreate.dao.IPreStudentDao;
import com.softcreate.model.PreStudent;
import com.softcreate.service.IPreStudentService;

@Service
public class PreStudentService implements IPreStudentService{

	@Resource 
	private IPreStudentDao preStudentDao;
	
	public List<PreStudent> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return preStudentDao.getPage(pageNo, pageSize);
	}

	public PreStudent getPreStudent(int id) {
		// TODO Auto-generated method stub
		return preStudentDao.getPreStudent(id);
	}

	public long count() {
		// TODO Auto-generated method stub
		return preStudentDao.count();
	}
	
	public boolean addPreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		return preStudentDao.savePreStudent(preStudent);
	}

	public boolean deletePreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		return preStudentDao.deletePreStudent(preStudent);
	}

	public boolean updatePreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		return preStudentDao.updatePreStudent(preStudent);
	}

	@Override
	public List<PreStudent> getAll(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return preStudentDao.getAll(fromDate, toDate);
	}

}
