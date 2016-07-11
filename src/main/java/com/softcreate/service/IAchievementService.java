package com.softcreate.service;

import java.util.List;

import com.softcreate.model.Achievement;
import com.softcreate.model.AchievementType;

public interface IAchievementService {

	AchievementType getAchievementType(int id);
	List<AchievementType> getAllAchievementType();
	
	List<Achievement> getAll(int typeId);
	
//	@RolesAllowed("ROLE_ADMIN")
	List<Achievement> getPage(int page, int pageSize);
	
//	@RolesAllowed("ROLE_ADMIN")
	Achievement getAchievement(int id);
	
//	@RolesAllowed("ROLE_ADMIN")
	long count();
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean addAchievement(Achievement achievement);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean deleteAchievement(Achievement achievement);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean updateAchievement(Achievement achievement);
	
}
