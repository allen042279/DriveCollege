package com.softcreate.dao.daoImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IAchievementDao;
import com.softcreate.model.Achievement;

@Repository
@Scope("singleton")
public class AchievementDaoImpl  extends BaseDaoImpl<Achievement> implements IAchievementDao {
	
	public long count() {
		// TODO Auto-generated method stub
		return findCount(Achievement.class);
	}

	public List<Achievement> getAll(int typeId) {
		// TODO Auto-generated method stub
		String hqlString = "From Achievement as s Where s.achievementType.id=" + typeId + " Order By s.showOrder";
		List<Achievement> achievements = find(hqlString);
		
		return achievements;
	}

	@Override
	public List<Achievement> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return findByPage("From Achievement as s Order By s.showOrder", pageNo, pageSize);
	}

	@Override
	public Achievement getAchievement(int id) {
		// TODO Auto-generated method stub
		return getById(Achievement.class, id);
	}

	@Override
	public boolean saveAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		try {
			save(achievement);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		try {
			update(achievement);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		try {
			delete(achievement);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
