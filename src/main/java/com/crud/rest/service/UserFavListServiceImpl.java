package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.UserFavList;
import com.crud.rest.dao.UserFavListDao;


/**
 * @author Archana
 *
 */
public class UserFavListServiceImpl implements UserFavListService{
	

	@Autowired
	private UserFavListDao userFavListDao;
	
	public void setUserFavListDao(UserFavListDao userFavListDao) {
		this.userFavListDao = userFavListDao;
	}

	@Override
	public List<UserFavList> getFavList(int userid) {
		// TODO Auto-generated method stub
		return userFavListDao.getFavList(userid);
	}

	@Override
	public List<UserFavList> getWatchedList(int userid) {
		// TODO Auto-generated method stub
		return userFavListDao.getWatchedList(userid);
	}

	@Override
	public void saveUserPreference(UserFavList favList) {
		// TODO Auto-generated method stub
		userFavListDao.saveUserPreference(favList);
	}

	@Override
	public void deleteUserPreference(UserFavList favList) {
		// TODO Auto-generated method stub
		userFavListDao.deleteUserPreference(favList);
	}

	
	
	

}
