package com.crud.rest.dao;

import java.util.List;

import com.crud.rest.beans.Movie;
import com.crud.rest.beans.UserFavList;

/**
 * @author Archana
 *
 */
public interface UserFavListDao {
	
	List<UserFavList> getFavList(int userid);
	
	List<UserFavList> getWatchedList(int userid);
	
	void saveUserPreference(UserFavList favList);
	
	void deleteUserPreference(UserFavList favList);
	
	

}
