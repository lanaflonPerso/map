package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.UserFavList;

/**
 * @author Archana
 *
 */
public interface UserFavListService {

	List<UserFavList> getFavList(int userid);

	List<UserFavList> getWatchedList(int userid);

	void saveUserPreference(UserFavList favList);

	void deleteUserPreference(UserFavList favList);
	
	

}
