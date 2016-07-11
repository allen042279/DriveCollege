package com.softcreate.dao.daoImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IAchievementTypeDao;
import com.softcreate.model.AchievementType;

@Repository
@Scope("singleton")
public class AchievementTypeDaoImpl  extends BaseDaoImpl<AchievementType> implements IAchievementTypeDao {
	

	public List<AchievementType> getAllAchievementType() {
		// TODO Auto-generated method stub
		List<AchievementType> achievementType = findAll(AchievementType.class);
		
		return achievementType;
	}
	
	public AchievementType get(int id) {
		// TODO Auto-generated method stub
		AchievementType achievementType = getById(AchievementType.class, id);
		
		return achievementType;
	}
}
