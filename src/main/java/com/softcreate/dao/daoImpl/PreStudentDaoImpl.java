package com.softcreate.dao.daoImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IPreStudentDao;
import com.softcreate.model.PreStudent;

@Repository
@Scope("singleton")
public class PreStudentDaoImpl  extends BaseDaoImpl<PreStudent> implements IPreStudentDao {
	
	public long count() {
		// TODO Auto-generated method stub
		return findCount(PreStudent.class);
	}

	public List<PreStudent> getAll(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		
		String hqlString = "From PreStudent as s Where s.applyDate between '" + fromDate + "' and '" + toDate + "' Order By s.applyDate DESC ";
		
		List<PreStudent> preStudents = find(hqlString);
		
		return preStudents;
	}

	@Override
	public List<PreStudent> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sqlString = "From PreStudent as s where s.dealwith = 0 Order By s.applyDate DESC ";
		return findByPage(sqlString, pageNo, pageSize);
	}

	@Override
	public PreStudent getPreStudent(int id) {
		// TODO Auto-generated method stub
		return getById(PreStudent.class, id);
	}

	@Override
	public boolean savePreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		try {
			save(preStudent);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		try {
			update(preStudent);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deletePreStudent(PreStudent preStudent) {
		// TODO Auto-generated method stub
		try {
			delete(preStudent);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
