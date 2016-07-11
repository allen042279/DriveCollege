package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.Achievement;

public interface IAchievementDao {

	long count();
	List<Achievement> getAll(int typeId);
	List<Achievement> getPage(int pageNo, int pageSize);
	Achievement getAchievement(int id);
	
	boolean saveAchievement(Achievement achievement);
	boolean updateAchievement(Achievement achievement);
	boolean deleteAchievement(Achievement achievement);

}
