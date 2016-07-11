package com.softcreate.service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.softcreate.dao.IAchievementDao;
import com.softcreate.dao.IAchievementTypeDao;
import com.softcreate.model.Achievement;
import com.softcreate.model.AchievementType;
import com.softcreate.service.IAchievementService;

@Service
public class AchievementService implements IAchievementService{

	@Resource private IAchievementDao achievementDao;
	
	@Resource private IAchievementTypeDao achievementTypeDao;
	
	@Override
	public List<Achievement> getAll(int typeId) {
		// TODO Auto-generated method stub
		return achievementDao.getAll(typeId);
	}
	
	public List<Achievement> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return achievementDao.getPage(pageNo, pageSize);
	}

	public Achievement getAchievement(int id) {
		// TODO Auto-generated method stub
		return achievementDao.getAchievement(id);
	}

	public long count() {
		// TODO Auto-generated method stub
		return achievementDao.count();
	}
	
	public boolean addAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		return achievementDao.saveAchievement(achievement);
	}

	public boolean deleteAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		return achievementDao.deleteAchievement(achievement);
	}

	public boolean updateAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		return achievementDao.updateAchievement(achievement);
	}

	@Override
	public List<AchievementType> getAllAchievementType() {
		// TODO Auto-generated method stub
		return achievementTypeDao.getAllAchievementType();
	}

	@Override
	public AchievementType getAchievementType(int id) {
		// TODO Auto-generated method stub
		return achievementTypeDao.get(id);
	}

}
