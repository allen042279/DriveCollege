package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.AchievementType;

public interface IAchievementTypeDao {

	AchievementType get(int id);
	List<AchievementType> getAllAchievementType();
}
